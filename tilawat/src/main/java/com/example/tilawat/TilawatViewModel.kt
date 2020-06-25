package com.example.tilawat

import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.PlaybackStateCompat
import androidx.lifecycle.*
import com.example.core.BaseViewModel
import com.example.data.audio.AudioMediaData
import com.example.data.audio.ServiceMetaData
import com.example.data.audio.isValid
import com.example.data.reciters.ReciterWrapper
import com.example.data.reciters.toWrapperList
import com.example.extensions.*
import com.example.media.media.connection.AudioServiceConnection
import com.example.media.media.connection.EMPTY_PLAYBACK_STATE
import com.example.media.media.connection.NOTHING_PLAYING
import com.example.media.media.extensions.*
import com.example.media.media.service.AudioService
import com.example.media.media.service.MediaHelper
import com.example.network.MainDispatcher
import com.example.network.error.ErrorType
import com.example.reciters.RecitersProvider
import com.example.repository.AudioMediaDataRepository
import com.example.repository.base.Status
import com.example.tilawat.dataprovider.IAudioData
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.util.ArrayList

typealias Reciters = List<ReciterWrapper>

class TilawatViewModel constructor(
    private val tilawatChapterProvider: TilawatChapterProvider,
    private val recitersProvider: RecitersProvider,
    private val audioConnection: AudioServiceConnection,
    private val audioDataProvider : IAudioData,
    private val audioMediaDataRepository: AudioMediaDataRepository
) : BaseViewModel() {

    init {
        viewModelScope.launch {
            Transformations.map(audioConnection.networkFailure) {
                it.isTrue {
                    onError(ErrorType.NETWORK)
                }
            }
        }
    }

    private var rootMediaId: String = MediaHelper.ROOT_ID
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
        recitersProvider.getReciters(this@TilawatViewModel)
            ?.recitations
            ?.toWrapperList()
            ?.also { list -> list.hasDataToShow { setCurrentReciter(first()) }
                emit(list)
            }
    }

    fun setCurrentReciter(reciter: ReciterWrapper) {
        tilawatChapterProvider.setCurrentReciter(reciter)
    }

    private val subscriptionCallback = object : MediaBrowserCompat.SubscriptionCallback() {
        override fun onChildrenLoaded(parentId: String, children: MutableList<MediaBrowserCompat.MediaItem>) {
            audioDataProvider.transformMediaItemDataToAudioMediaData(children.sortedBy { it.mediaId }.castToMutableList) {
                mediaMetaData.nonNull {
                    playMediaIfHasValidId(this)
                }
            }
        }
    }

    private val playbackStateObserver = Observer<PlaybackStateCompat> {
        playbackState = it ?: EMPTY_PLAYBACK_STATE
        val metadata = audioConnection.nowPlaying.value ?: NOTHING_PLAYING
        metadata.getString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID).nonNull {
            postMetadataToUI(playbackState, metadata)
        }
    }

    private fun postMetadataToUI(playbackState: PlaybackStateCompat, mediaMetadata: MediaMetadataCompat) {
        audioDataProvider.updateCurrentVerse(mediaMetadata.trackNumber)
        audioMetaData.postValue(audioDataProvider.getCurrentAudioMetaData(playbackState, mediaMetadata))
    }

    private val mediaMetadataObserver = Observer<MediaMetadataCompat> {
        val playbackState = audioConnection.playbackState.value ?: EMPTY_PLAYBACK_STATE
        val metadata = it ?: NOTHING_PLAYING
        metadata.getString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID).nonNull {
            postMetadataToUI(playbackState, metadata)
        }
    }

    private val localAudioConnection = audioConnection.also {
        it.subscribe(rootMediaId, subscriptionCallback)
        it.playbackState.observeForever(playbackStateObserver)
        it.nowPlaying.observeForever(mediaMetadataObserver)
        checkForPlaybackPosition()
    }

    private fun checkForPlaybackPosition() {
        viewModelScope.launch {
            delay(100L)
            ((playbackState.isPlaying) and (currentDurationLiveData.value != playbackState.currentPlayBackPosition)).isTrue {
                currentDurationLiveData.postValue(playbackState.currentPlayBackPosition)
                audioDataProvider.getCurrentPlayingMetadata().nonNull {
                    audioProgress = this@TilawatViewModel.playbackState.currentPlayBackPosition
                    displayableProgress = audioProgress.toTimeStamp()
                }
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
            unsubscribe(this@TilawatViewModel.rootMediaId, subscriptionCallback)
        }
        needToUpdatePosition = false
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

    private fun sendDataToService(list: List<AudioMediaData>) {
        audioDataProvider.apply {
            updateList(list)
            sendCommandToService(mapMetaDataFromList())
        }
    }

    private fun fetchFromRepository(verseNumber: Int) {
        viewModelScope.launch {
            audioMediaDataRepository.fetchDataFor(audioDataProvider.buildAudioHelperData(verseNumber)) {
                when(status) {
                    Status.LOADING -> {}
                    Status.SUCCESS -> {
                        sendDataToService(data!!)
                    }
                    Status.FAILURE -> {
                        data.nonNull {
                            sendDataToService(this)
                        }
                        onError(error)
                    }
                }
            }
        }
    }

    private fun sendCommandToService(metaData: List<ServiceMetaData>) {
        viewModelScope.launch {
            withContext(MainDispatcher) {
                audioConnection.sendCommand(AudioService.REFRESH_AUDIO_DATA, Bundle().apply {
                    putSerializable(AudioService.AUDIO_DATA, metaData as ArrayList<ServiceMetaData>)
                }) { _, _ -> }
            }
        }
    }

    fun doFetchOrPlay(verseNumber: Int) {
        audioDataProvider.canPlayFromLocalList(verseNumber) {
            it.isTrue {
                audioDataProvider.getCurrentPlayingMediaMetadata().nonNull {
                    playMediaIfHasValidId(this)
                }
            } ?: fetchFromRepository(verseNumber)
        }
    }

    private fun playMediaIfHasValidId(mediaMetadata: AudioMediaData.MediaMetaData) {
        mediaMetadata.apply {
            isValid.isTrue { playMediaId(mediaId) } ?: onError(ErrorType.INVALID_AUDIO_DATA)
        }
    }
}