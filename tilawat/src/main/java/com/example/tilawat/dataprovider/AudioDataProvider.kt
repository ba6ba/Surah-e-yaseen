package com.example.tilawat.dataprovider

import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.PlaybackStateCompat
import com.example.data.audio.*
import com.example.data.reciters.defaultReciter
import com.example.extensions.*
import com.example.media.media.extensions.isPlaying
import com.example.shared.getSurahYaseen
import com.example.tilawat.*
import org.koin.core.KoinComponent
import org.koin.core.inject

class AudioDataProvider : IAudioData, KoinComponent {

    private var currentVersePlaying: Int = 0
    private val audioItemsList: ArrayList<AudioMediaData> = arrayListOf()
    private val tilawatChapterProvider: TilawatChapterProvider by inject()

    override fun canPlayFromLocalList(verseNumber: Int, fetchFromRepository: (Boolean) -> Unit) {
        currentVersePlaying = verseNumber
        fetchFromRepository(getAll().find { it.data?.id == currentVersePlaying } != null)
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
                    mediaUri = children[index].description.mediaUri.toString()
                }
            }
        }.find { it.data?.id == currentVersePlaying }.nonNull(itemToPlay)
    }

    override fun getCurrentAudioMetaData(
        playbackState: PlaybackStateCompat,
        mediaMetadata: MediaMetadataCompat
    ): AudioMediaData.MetaData? =
        getAll().find { it.data?.id == currentVersePlaying }?.metaData
            ?.copy(
                playbackState = State.get(playbackState.isPlaying, getPlayingState, getPauseState),
                number = currentVersePlaying.toLong()
            )

    override fun updateCurrentVerse(verseNumber: Number) {
        currentVersePlaying = verseNumber.toInt()
    }

    override fun buildAudioHelperData(verseNumber: Int): AudioHelperData =
        AudioHelperData(
            currentAudioId = AudioMediaIdProvider.generate(verseNumber, getSurahYaseen),
            audioClipModel = createAudioClipModel(verseNumber),
            title = tilawatChapterProvider.surahName,
            authorData = tilawatChapterProvider.authorData!!,
            numberOfAudioItems = tilawatChapterProvider.tilawatChapterData.numberOfVerses,
            currentNumber = verseNumber
        )

    override fun updateList(list: List<AudioMediaData>) {
        audioItemsList.clear()
        audioItemsList.addAll(list)
    }

    private fun createAudioClipModel(verseNumber: Int): AudioClipModel =
        AudioClipModel(
            chapterId = getSurahYaseen, reciterId = tilawatChapterProvider.tilawatChapterData.reciterId ?: defaultReciter.id,
            verseId = tilawatChapterProvider.tilawatAudioClipRange.getMemberFromIndex(verseNumber)
        )

    override fun getAll(): List<AudioMediaData> = audioItemsList.apply {
        sortedBy { it.data?.audioId }
    }

    override fun getCurrentPlayingMediaMetadata(): AudioMediaData.MediaMetaData =
        getAll().find { it.data?.id == currentVersePlaying }?.mediaMetaData!!

    override fun getCurrentPlayingMetadata(): AudioMediaData.MetaData? =
        getAll().find { it.data?.id == currentVersePlaying }?.metaData

    override fun get(index: Int): AudioMediaData = getAll()[index]

    override val authorData: AudioMediaData.AuthorData by lazy {
        AudioMediaData.AuthorData()
    }

    override val mediaMetadata: AudioMediaData.MediaMetaData by lazy {
        AudioMediaData.MediaMetaData()
    }

    override fun mapMetaDataFromList(): List<ServiceMetaData> =
        getAll().map {
            it.toServiceMetaData
        }

    private val getPlayingState: State
        get() = State.PLAYING
            .apply {
                imageRes = R.drawable.ic_play_icon
            }

    private val getPauseState: State
        get() = State.PAUSE
            .apply {
                imageRes = R.drawable.ic_pause_icon
            }
}