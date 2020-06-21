package com.example.tilawat

import androidx.lifecycle.MutableLiveData
import com.example.data.Chapter
import com.example.data.AudioClipModel
import com.example.data.reciters.ReciterWrapper
import com.example.data.reciters.defaultReciter
import com.example.data.reciters.toAuthorData
import com.example.extensions.getMemberFromIndex
import com.example.network.error.ErrorHandler
import com.example.network.repository.AudioNetworkRepository
import com.example.shared.getSurahYaseen

class TilawatChapterProvider(private val audioNetworkRepository: AudioNetworkRepository) {

    var getTilawatChapterLiveData: MutableLiveData<TilawatChapterData> = MutableLiveData()
    var tilawatChapterData: TilawatChapterData = TilawatChapterData()
    private val tilawatAudioClipRange: IntRange by lazy {
        tilawatChapterData.getRangeForAudioVerses
    }
    var chapter: Chapter? = null
        set(value) {
            field = value
            tilawatChapterData = convertChapterToTilawatChapterData
            postTilawatChapterLiveData()
        }

    fun setCurrentReciter(reciter: ReciterWrapper) {
        tilawatChapterData.reciter = reciter
        postTilawatChapterLiveData()
    }

    private fun postTilawatChapterLiveData() {
        getTilawatChapterLiveData.postValue(tilawatChapterData)
    }

    private val convertChapterToTilawatChapterData
        get() = chapter.toTilawatChapterData()

    suspend fun getAudioData(number: Int, errorHandler: ErrorHandler) = audioNetworkRepository.getAudioClipData(getAudioModel(number), errorHandler)

    private fun getAudioModel(number: Int) =
        AudioClipModel(getSurahYaseen, tilawatAudioClipRange.getMemberFromIndex(number),
            tilawatChapterData.reciterId ?: defaultReciter.id)
            .also {
                tilawatChapterData.currentVerseNumber = number
            }
}

val TilawatChapterProvider.authorData
    get() = tilawatChapterData.reciter?.reciter?.toAuthorData

val TilawatChapterProvider.surahName
    get() = tilawatChapterData.surahNameEnglish

val TilawatChapterProvider.number
    get() = tilawatChapterData.currentVerseNumber