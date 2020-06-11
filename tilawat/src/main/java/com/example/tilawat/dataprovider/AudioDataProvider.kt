package com.example.tilawat.dataprovider

import android.content.Context
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.PlaybackStateCompat
import com.example.data.audio.Audio
import com.example.data.audio.AudioMediaData
import com.example.data.audio.setBitmap
import com.example.data.audio.toServiceMetaData
import com.example.extensions.*
import com.example.media.media.extensions.isPlaying
import com.example.tilawat.*
import org.koin.core.KoinComponent
import org.koin.core.inject

typealias State = AudioMediaData.PlaybackState

class AudioDataProvider : IAudioData, KoinComponent {

    private var currentVersePlaying: Int = 0
    private val audioItemsList: ArrayList<AudioMediaData> = listWithCapacity(0)
    private val tilawatChapterProvider: TilawatChapterProvider by inject()
    private val context: Context by inject()

    override fun loadAudioData(audio: Audio?, callBack: List<AudioMediaData.ServiceMetaData>.() -> Unit) {
        audio.nonNull {
            createAudioMediaData(this)
                .also {
                    audioItemsList.add(it)
                }
            callBack(mapMetaDataFromList())
        }
    }

    override fun fetchFromRemoteOrPlayFromLocal(verseNumber: Int, fetchFromRemote: (Boolean) -> Unit) {
        currentVersePlaying = verseNumber
        fetchFromRemote(getAll().find { it.data?.id == currentVersePlaying } != null)
    }

    override fun transformMediaItemDataToAudioMediaData(
        children: MutableList<MediaBrowserCompat.MediaItem>,
        itemToPlay: AudioMediaData.() -> Unit
    ) {
        getAll().apply {
            forEachIndexed { index, audioItem ->
                audioItem.mediaMetaData?.apply {
                    mediaId = children[index].mediaId!!
                    isBrowsable = children[index].isBrowsable
                    isPlayable = children[index].isPlayable
                    mediaUri = children[index].description.mediaUri!!
                }
            }
        }.find { it.data?.id == currentVersePlaying }.nonNull(itemToPlay)
    }

    override fun getCurrentAudioMetaData(
        playbackState: PlaybackStateCompat,
        mediaMetadata: MediaMetadataCompat
    ): AudioMediaData.MetaData? =
        getAll().find { it.data?.id == currentVersePlaying }?.metaData
            ?.copy(playbackState = State.get(playbackState.isPlaying, getPlayingState, getPauseState))

    override fun getAll(): List<AudioMediaData> = audioItemsList.apply {
        sortedBy { it.data?.audioId }
    }

    override fun getCurrentPlayingMediaMetadata(): AudioMediaData.MediaMetaData =
        getAll().find { it.data?.id == currentVersePlaying }?.mediaMetaData!!

    override fun get(index: Int): AudioMediaData = getAll()[index]

    override val data: AudioMediaData.Data by lazy {
        AudioMediaData.Data()
    }

    override val metadata: AudioMediaData.MetaData by lazy {
        AudioMediaData.MetaData()
    }

    override val authorData: AudioMediaData.AuthorData by lazy {
        AudioMediaData.AuthorData()
    }

    override val mediaMetadata: AudioMediaData.MediaMetaData by lazy {
        AudioMediaData.MediaMetaData()
    }

    override val imageMetadata: AudioMediaData.ImageMetaData by lazy {
        AudioMediaData.ImageMetaData()
    }

    private fun createAudioMediaData(audio: Audio) = AudioMediaData.build {
        authorData = tilawatChapterProvider.authorData
        imageMetaData = imageMetadata.copy(imageDrawableRes = R.drawable.splash_logo)
            .apply { setBitmap(context) }
        title = tilawatChapterProvider.surahName
        metaData = this@AudioDataProvider.metadata.copy(
            url = audio.url, format = audio.format, audioDuration = audio.duration.toSeconds,
            displayableDuration = audio.duration.toSeconds.toTimeStamp(),
            playbackState = getPlayingState
        )
        data = this@AudioDataProvider.data.copy(
            totalNumber = tilawatChapterProvider.tilawatChapterData.numberOfVerses, audioId = audio.url.getIdFromUrl(),
            id = currentVersePlaying
        )
    }

    private fun mapMetaDataFromList(): List<AudioMediaData.ServiceMetaData> =
        getAll().map {
            it.toServiceMetaData
        }

    private val getPlayingState: State
        get() = State.PLAYING
            .apply {
                imageRes = R.drawable.play_icon
            }

    private val getPauseState: State
        get() = State.PAUSE
            .apply {
                imageRes = R.drawable.stop_icon
            }
}