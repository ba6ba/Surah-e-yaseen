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
import com.example.data.Audio
import com.example.data.reciters.ReciterWrapper
import com.example.data.reciters.toWrapperList
import com.example.extensions.hasData
import com.example.extensions.hasDataToShow
import com.example.extensions.isTrue
import com.example.media.media.AudioServiceConnection
import com.example.media.media.EMPTY_PLAYBACK_STATE
import com.example.media.media.NOTHING_PLAYING
import com.example.media.media.extensions.id
import com.example.media.media.extensions.isPlayEnabled
import com.example.media.media.extensions.isPlaying
import com.example.media.media.extensions.isPrepared
import com.example.media.media.service.AudioService
import com.example.media.media.service.MediaHelper
import com.example.media.media.source.AudioClipData
import com.example.network.error.ApiErrorType
import com.example.reciters.RecitersProvider
import timber.log.Timber

typealias Reciters = List<ReciterWrapper>

class TilawatViewModel constructor(
    private val tilawatChapterProvider: TilawatChapterProvider,
    private val recitersProvider: RecitersProvider,
    private val audioConnection: AudioServiceConnection,
    private val context : Context
) : BaseViewModel() {

    private var mediaId : String = MediaHelper.ROOT_ID
    val audioItems = MutableLiveData<List<AudioClipData>>()
    val tilawatChapterData: MutableLiveData<TilawatChapterData> = tilawatChapterProvider.getTilawatChapterLiveData

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
            audioItems.postValue(
                children.map { child ->
                    val subtitle = child.description.subtitle ?: ""
                    AudioClipData(
                        child.mediaId!!,
                        child.description.title.toString(),
                        subtitle.toString(),
                        child.description.iconUri ?: Uri.EMPTY,
                        child.isBrowsable,
                        getResourceForMediaId(child.mediaId!!)
                    )
                }
            )
        }
    }

    private val playbackStateObserver = Observer<PlaybackStateCompat> {
        val playbackState = it ?: EMPTY_PLAYBACK_STATE
        val metadata = audioConnection.nowPlaying.value ?: NOTHING_PLAYING
        if (metadata.getString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID) != null) {
            audioItems.postValue(updateState(playbackState, metadata))
        }
    }

    private val mediaMetadataObserver = Observer<MediaMetadataCompat> {
        val playbackState = audioConnection.playbackState.value ?: EMPTY_PLAYBACK_STATE
        val metadata = it ?: NOTHING_PLAYING
        if (metadata.getString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID) != null) {
            audioItems.postValue(updateState(playbackState, metadata))
        }
    }

    private val localAudioConnection = audioConnection.also {
        it.subscribe(mediaId, subscriptionCallback)
        it.playbackState.observeForever(playbackStateObserver)
        it.nowPlaying.observeForever(mediaMetadataObserver)
    }

    override fun onCleared() {
        super.onCleared()
        localAudioConnection.apply {
            playbackState.removeObserver(playbackStateObserver)
            nowPlaying.removeObserver(mediaMetadataObserver)
            unsubscribe(mediaId, subscriptionCallback)
        }
    }

    private fun updateState(
        playbackState: PlaybackStateCompat,
        mediaMetadata: MediaMetadataCompat
    ): List<AudioClipData> {

        val newResId = when (playbackState.isPlaying) {
            true -> R.drawable.stop_icon
            else -> R.drawable.play_icon
        }

        return audioItems.value?.map {
            val useResId = if (it.mediaId == mediaMetadata.id) newResId else NO_RES
            it.copy(playbackRes = useResId)
        } ?: emptyList()
    }

    private fun getResourceForMediaId(mediaId: String): Int {
        val isActive = mediaId == audioConnection.nowPlaying.value?.id
        val isPlaying = audioConnection.playbackState.value?.isPlaying ?: false
        return when {
            !isActive -> NO_RES
            isPlaying -> R.drawable.stop_icon
            else -> R.drawable.play_icon
        }
    }

    fun playMedia(audioClipData: AudioClipData, pauseAllowed: Boolean = true) {
        playMediaId(audioClipData.mediaId, pauseAllowed)
    }

    fun playMediaId(mediaId: String, pauseAllowed: Boolean = true) {
        val nowPlaying = audioConnection.nowPlaying.value
        val transportControls = audioConnection.transportControls

        val isPrepared = audioConnection.playbackState.value?.isPrepared ?: false
        if (isPrepared && mediaId == nowPlaying?.id) {
            audioConnection.playbackState.value?.let { playbackState ->
                when {
                    playbackState.isPlaying -> if (pauseAllowed) transportControls.pause() else Unit
                    playbackState.isPlayEnabled -> transportControls.play()
                    else -> {
                        Timber.w("""Playable item clicked but neither play nor 
                            |pause are enabled! (mediaId=$mediaId)""".trimMargin()
                        )
                    }
                }
            }
        } else {
            transportControls.playFromMediaId(mediaId, null)
        }
    }

    fun fetchAudioForVerse(verseNumber : Int) = liveData<Unit> {
        tilawatChapterProvider.getAudio(verseNumber).apply {
            if (this != null && this.audio != null) {
                sendBroadcastToServiceViaIntent(this.audio!!)
            } else {
                onError(ApiErrorType.UNKNOWN)
            }
        }
    }

    private fun sendBroadcastToServiceViaIntent(audio: Audio) {
        context.apply {
            ContextCompat.startForegroundService(this,
                createAudioServiceIntent(AudioService.NAME, AudioService.PLAY_AUDIO, audio))
        }
    }

    fun playAudio(audio: Int) {
        audioItems.value?.let { list ->
            list.hasData {
                playMedia(it[audio])
            }
        }
    }
}

private const val NO_RES = 0

fun Context.createAudioServiceIntent(name: String, action: String, data: Audio) =
    Intent(this, Class.forName(name))
        .apply {
            this.action = action
            putExtra(AudioService.AUDIO_DATA, data)
        }