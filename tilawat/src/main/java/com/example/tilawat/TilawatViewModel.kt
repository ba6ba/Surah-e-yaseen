package com.example.tilawat

import android.content.Context
import android.content.Intent
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.PlaybackStateCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.*
import com.example.core.BaseViewModel
import com.example.data.audio.*
import com.example.data.reciters.ReciterWrapper
import com.example.data.reciters.toWrapperList
import com.example.extensions.*
import com.example.media.media.connection.AudioServiceConnection
import com.example.media.media.connection.EMPTY_PLAYBACK_STATE
import com.example.media.media.connection.NOTHING_PLAYING
import com.example.media.media.extensions.*
import com.example.media.media.service.AudioService
import com.example.media.media.service.MediaHelper
import com.example.network.error.ApiErrorType
import com.example.reciters.RecitersProvider
import com.example.shared.Do
import com.example.tilawat.dataprovider.IAudioData
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.ArrayList

typealias Reciters = List<ReciterWrapper>

class TilawatViewModel constructor(
    private val context: Context,
    private val tilawatChapterProvider: TilawatChapterProvider,
    private val recitersProvider: RecitersProvider,
    private val audioConnection: AudioServiceConnection,
    private val audioDataProvider : IAudioData
) : BaseViewModel() {

    init {
        viewModelScope.launch {
            Transformations.map(audioConnection.networkFailure) {
                it.isTrue {
                    onError(ApiErrorType.NETWORK)
                }
            }
        }
    }

    private var mediaId: String = MediaHelper.ROOT_ID
    private var needToUpdatePosition: Boolean = true
    private var playbackState = EMPTY_PLAYBACK_STATE
    var formatToDisplayVerseCount: String = "%02d"
        set(value) {
            field = "%0${value}d"
        }
    val tilawatChapterData: MutableLiveData<TilawatChapterData> = tilawatChapterProvider.getTilawatChapterLiveData
    val audioMetaData: MutableLiveData<AudioMediaData.MetaData> = MutableLiveData()
    val currentDurationLiveData = MutableLiveData<Long>()
        .apply {
            postValue(0L)
        }

    fun getTranslators(): LiveData<Reciters> = liveData {
        (recitersProvider.getReciters(this@TilawatViewModel)?.recitations.toWrapperList() ?: arrayListOf()).also { list ->
            list.hasDataToShow {
                setCurrentReciter(first())
            }
            emit(list)
        }
    }

    fun setCurrentReciter(reciter: ReciterWrapper) {
        tilawatChapterProvider.setCurrentReciter(reciter)
    }

    private val subscriptionCallback = object : MediaBrowserCompat.SubscriptionCallback() {
        override fun onChildrenLoaded(parentId: String, children: MutableList<MediaBrowserCompat.MediaItem>) {
            audioDataProvider.transformMediaItemDataToAudioMediaData(children) {
                mediaMetaData.nonNull {
                    playMedia(this)
                }
            }
        }
    }

    private val playbackStateObserver = Observer<PlaybackStateCompat> {
        playbackState = it ?: EMPTY_PLAYBACK_STATE
        val metadata = audioConnection.nowPlaying.value ?: NOTHING_PLAYING
        if (metadata.getString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID) != null) {
            updateState(playbackState, metadata)
            postMetadataToUI(playbackState, metadata)
        }
    }

    private fun postMetadataToUI(playbackState: PlaybackStateCompat, mediaMetadata: MediaMetadataCompat) {
        audioMetaData.postValue(audioDataProvider.getCurrentAudioMetaData(playbackState, mediaMetadata))
    }

    private val mediaMetadataObserver = Observer<MediaMetadataCompat> {
        val playbackState = audioConnection.playbackState.value ?: EMPTY_PLAYBACK_STATE
        val metadata = it ?: NOTHING_PLAYING
        if (metadata.getString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID) != null) {
            updateState(playbackState, metadata)
        }
    }

    private val localAudioConnection = audioConnection.also {
        it.subscribe(mediaId, subscriptionCallback)
        it.playbackState.observeForever(playbackStateObserver)
        it.nowPlaying.observeForever(mediaMetadataObserver)
        checkForPlaybackPosition()
    }

    private fun checkForPlaybackPosition() {
        viewModelScope.launch {
            delay(100L)
            (currentDurationLiveData.value != playbackState.currentPlayBackPosition).isTrue {
                currentDurationLiveData.postValue(playbackState.currentPlayBackPosition)
            }
            needToUpdatePosition.isTrue {
                checkForPlaybackPosition()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        localAudioConnection.apply {
            playbackState.removeObserver(playbackStateObserver)
            nowPlaying.removeObserver(mediaMetadataObserver)
            unsubscribe(mediaId, subscriptionCallback)
        }
        needToUpdatePosition = false
    }

    private fun updateState(
        playbackState: PlaybackStateCompat,
        mediaMetadata: MediaMetadataCompat
    ): List<AudioMediaData> = audioDataProvider.getAll().apply {
        map {
            it.metaData?.playbackState = if (mediaMetadata.id == it.mediaMetaData?.mediaId && playbackState.isPlaying)
                AudioMediaData.PlaybackState.PLAYING else AudioMediaData.PlaybackState.PAUSE
        }
    }

    private fun playMedia(mediaMetadata: AudioMediaData.MediaMetaData) {
        audioConnection.nowPlaying.value?.apply {
//            if (id == mediaMetadata.mediaId) id ?: mediaMetadata.mediaId else mediaMetadata.mediaId
            playMediaId(mediaMetadata.mediaId)
        }
    }

    private fun playMediaId(mediaId: String) {
        val nowPlaying = audioConnection.nowPlaying.value
        val transportControls = audioConnection.transportControls
        val isPrepared = audioConnection.playbackState.value?.isPrepared ?: false
        if (isPrepared && mediaId == nowPlaying?.id) {
            audioConnection.playbackState.value?.let { playbackState ->
                when {
                    playbackState.isPlaying -> transportControls.pause()
                    playbackState.isPlayEnabled -> transportControls.play()
                    else -> {
                        Timber.w(
                            """Playable item clicked but neither play nor 
                            |pause are enabled! (mediaId=$mediaId)""".trimMargin()
                        )
                    }
                }
            }
        } else {
            transportControls.playFromMediaId(mediaId, null)
        }
    }

    private fun fetchAudioForVerse(verseNumber: Int) {
        viewModelScope.launch {
            tilawatChapterProvider.getAudioData(verseNumber, this@TilawatViewModel).nonNull {
                audioDataProvider.loadAudioData(audio) {
                    sendBroadcastToServiceViaIntent(this)
                }
            }
        }
    }

    private fun sendBroadcastToServiceViaIntent(metaData: List<AudioMediaData.ServiceMetaData>) {
        context.apply {
            ContextCompat.startForegroundService(
                this,
                createAudioServiceIntent(AudioService.NAME, AudioService.PLAY_AUDIO, metaData)
            )
        }
    }

    fun doFetchOrPlay(verseNumber: Int) {
        audioDataProvider.fetchFromRemoteOrPlayFromLocal(verseNumber) {
            it.isTrue {
                playMediaId(audioDataProvider.getCurrentPlayingMediaMetadata().mediaId)
            } ?: fetchAudioForVerse(verseNumber)
        }
    }
}

fun Context.createAudioServiceIntent(name: String, action: String, data: List<AudioMediaData.ServiceMetaData>) =
    Intent(this, Class.forName(name))
        .apply {
            this.action = action
            putExtra(AudioService.AUDIO_DATA, data as ArrayList<AudioMediaData.ServiceMetaData>)
        }