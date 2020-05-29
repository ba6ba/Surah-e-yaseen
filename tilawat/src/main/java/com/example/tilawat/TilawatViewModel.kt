package com.example.tilawat

import android.net.Uri
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.PlaybackStateCompat
import androidx.lifecycle.*
import com.example.core.BaseViewModel
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
import com.example.media.media.service.MediaHelper
import com.example.media.media.source.AudioClipData
import com.example.network.error.ApiErrorType
import com.example.reciters.RecitersProvider
import timber.log.Timber

typealias Reciters = List<ReciterWrapper>

class TilawatViewModel constructor(
    private val tilawatChapterProvider: TilawatChapterProvider,
    private val recitersProvider: RecitersProvider,
    private val audioConnection: AudioServiceConnection
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

    /**
     * When the session's [PlaybackStateCompat] changes, the [mediaItems] need to be updated
     * so the correct [MediaItemData.playbackRes] is displayed on the active item.
     * (i.e.: play/pause button or blank)
     */
    private val playbackStateObserver = Observer<PlaybackStateCompat> {
        val playbackState = it ?: EMPTY_PLAYBACK_STATE
        val metadata = audioConnection.nowPlaying.value ?: NOTHING_PLAYING
        if (metadata.getString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID) != null) {
            audioItems.postValue(updateState(playbackState, metadata))
        }
    }

    /**
     * When the session's [MediaMetadataCompat] changes, the [mediaItems] need to be updated
     * as it means the currently active item has changed. As a result, the new, and potentially
     * old item (if there was one), both need to have their [MediaItemData.playbackRes]
     * changed. (i.e.: play/pause button or blank)
     */
    private val mediaMetadataObserver = Observer<MediaMetadataCompat> {
        val playbackState = audioConnection.playbackState.value ?: EMPTY_PLAYBACK_STATE
        val metadata = it ?: NOTHING_PLAYING
        if (metadata.getString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID) != null) {
            audioItems.postValue(updateState(playbackState, metadata))
        }
    }

    /**
     * Because there's a complex dance between this [ViewModel] and the [audioConnection]
     * (which is wrapping a [MediaBrowserCompat] object), the usual guidance of using
     * [Transformations] doesn't quite work.
     *
     * Specifically there's three things that are watched that will cause the single piece of
     * [LiveData] exposed from this class to be updated.
     *
     * [subscriptionCallback] (defined above) is called if/when the children of this
     * ViewModel's [mediaId] changes.
     *
     * [audioConnection.playbackState] changes state based on the playback state of
     * the player, which can change the [MediaItemData.playbackRes]s in the list.
     *
     * [audioConnection.nowPlaying] changes based on the item that's being played,
     * which can also change the [MediaItemData.playbackRes]s in the list.
     */
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

    /**
     * This method takes a [MediaItemData] and does one of the following:
     * - If the item is *not* the active item, then play it directly.
     * - If the item *is* the active item, check whether "pause" is a permitted command. If it is,
     *   then pause playback, otherwise send "play" to resume playback.
     */
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

    fun playAudio(audio: Int) {
        audioItems.value?.let { list ->
            list.hasData {
                playMedia(it[audio])
            }
        }
    }
}

private const val NO_RES = 0