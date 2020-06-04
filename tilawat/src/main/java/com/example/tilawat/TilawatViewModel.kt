package com.example.tilawat

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.PlaybackStateCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.*
import com.example.core.BaseViewModel
import com.example.data.audio.NotificationAudioWrapper
import com.example.data.reciters.ReciterWrapper
import com.example.data.reciters.toWrapperList
import com.example.extensions.hasDataToShow
import com.example.extensions.isTrue
import com.example.extensions.toTimeStamp
import com.example.media.media.connection.AudioServiceConnection
import com.example.media.media.connection.EMPTY_PLAYBACK_STATE
import com.example.media.media.connection.NOTHING_PLAYING
import com.example.media.media.extensions.*
import com.example.media.media.service.AudioService
import com.example.media.media.service.MediaHelper
import com.example.media.media.source.AudioClipData
import com.example.network.error.ApiErrorType
import com.example.reciters.RecitersProvider
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

typealias Reciters = List<ReciterWrapper>

class TilawatViewModel constructor(
    private val tilawatChapterProvider: TilawatChapterProvider,
    private val recitersProvider: RecitersProvider,
    private val audioConnection: AudioServiceConnection,
    private val context: Context
) : BaseViewModel() {

    private var mediaId: String = MediaHelper.ROOT_ID
    var audioItems: List<AudioClipData> = arrayListOf()
    val tilawatChapterData: MutableLiveData<TilawatChapterData> = tilawatChapterProvider.getTilawatChapterLiveData
    val playAudioLiveData: MutableLiveData<AudioClipData> = MutableLiveData()
    val audioMetaData: MutableLiveData<AudioClipData.Metadata> = MutableLiveData()
    private var needToUpdatePosition: Boolean = true
    private var playbackState = EMPTY_PLAYBACK_STATE
    var formatToDisplayVerseCount: String = "%02d"
        set(value) {
            field = "%0${value}d"
        }
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

    val networkError = Transformations.map(audioConnection.networkFailure) {
        it.isTrue {
            onError(ApiErrorType.NETWORK)
        }
    }

    private val subscriptionCallback = object : MediaBrowserCompat.SubscriptionCallback() {
        override fun onChildrenLoaded(parentId: String, children: MutableList<MediaBrowserCompat.MediaItem>) {
            audioItems = children.map { child ->
                val subtitle = child.description.subtitle ?: ""
                AudioClipData(
                    child.mediaId!!,
                    child.description.title.toString(),
                    subtitle.toString(),
                    child.description.iconUri ?: Uri.EMPTY,
                    child.isBrowsable,
                    getPlayingStateForMediaId(child.mediaId!!),
                    child.description.mediaUri
                )
            }
            playAudioLiveData.value = audioItems.first()
        }
    }

    private val playbackStateObserver = Observer<PlaybackStateCompat> {
        playbackState = it ?: EMPTY_PLAYBACK_STATE
        val metadata = audioConnection.nowPlaying.value ?: NOTHING_PLAYING
        if (metadata.getString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID) != null) {
            audioItems = updateState(playbackState, metadata)
            postMetadataToUI(playbackState, metadata)
        }
    }

    private fun postMetadataToUI(playbackState: PlaybackStateCompat, mediaMetadata: MediaMetadataCompat) {
        audioMetaData.postValue(
            AudioClipData.Metadata(
                mediaMetadata.title ?: "",
                mediaMetadata.displaySubtitle ?: "",
                mediaMetadata.author ?: "",
                playbackState.isPlaying,
                mediaMetadata.albumArtUri,
                mediaMetadata.albumArt,
                mediaMetadata.duration.toTimeStamp(),
                mediaMetadata.duration
            )
        )
    }

    private val mediaMetadataObserver = Observer<MediaMetadataCompat> {
        val playbackState = audioConnection.playbackState.value ?: EMPTY_PLAYBACK_STATE
        val metadata = it ?: NOTHING_PLAYING
        if (metadata.getString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID) != null) {
            audioItems = updateState(playbackState, metadata)
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
    ): List<AudioClipData> {
        return audioItems.map {
            it.copy(playing = if ((it.mediaId == mediaMetadata.id)) playbackState.isPlaying else false)
        }
    }

    private fun getPlayingStateForMediaId(mediaId: String): Boolean =
        mediaId == audioConnection.nowPlaying.value?.id && audioConnection.playbackState.value?.isPlaying ?: false

    private fun playMedia(audioClipData: AudioClipData) {
        audioConnection.nowPlaying.value?.apply {
            playMediaId(if (mediaUri == audioClipData.mediaUri) id ?: audioClipData.mediaId else audioClipData.mediaId)
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

    fun fetchAudioForVerse(verseNumber: Int) = liveData<Unit> {
        tilawatChapterProvider.getAudio(verseNumber).apply {
            if (this != null) {
                sendBroadcastToServiceViaIntent(this)
            } else {
                onError(ApiErrorType.UNKNOWN)
            }
        }
    }

    private fun sendBroadcastToServiceViaIntent(audioWrapper: NotificationAudioWrapper) {
        context.apply {
            ContextCompat.startForegroundService(
                this,
                createAudioServiceIntent(AudioService.NAME, AudioService.PLAY_AUDIO, audioWrapper)
            )
        }
    }

    fun playAudio() {
        playAudioLiveData.value?.let { audioItem ->
            playMedia(audioItem)
        }
    }

    fun doFetchOrPlay(verseNumber: Int) {
//        audioConnection.nowPlaying.value?.trackNumber == verseNumber
    }
}

fun Context.createAudioServiceIntent(name: String, action: String, data: NotificationAudioWrapper) =
    Intent(this, Class.forName(name))
        .apply {
            this.action = action
            putExtra(AudioService.AUDIO_DATA, data)
        }