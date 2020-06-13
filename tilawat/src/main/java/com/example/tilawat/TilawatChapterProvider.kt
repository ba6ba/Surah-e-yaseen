package com.example.tilawat

import androidx.lifecycle.MutableLiveData
import com.example.data.Chapter
import com.example.data.TilawatAudioModel
import com.example.data.reciters.ReciterWrapper
import com.example.data.reciters.toAuthorData
import com.example.extensions.getMemberFromIndex
import com.example.network.error.ErrorHandler
import com.example.network.repository.TilawatRepository
import com.example.shared.getSurahYaseen

class TilawatChapterProvider(private val tilawatRepository: TilawatRepository) {

    var getTilawatChapterLiveData: MutableLiveData<TilawatChapterData> = MutableLiveData()
    var tilawatChapterData: TilawatChapterData = TilawatChapterData()
    private val tilawatAudioClipRange: IntRange by lazy {
        tilawatChapterData.getRangeForAudioVerses
    }

    var chapter: Chapter? = null
        set(value) {
            field = value
            tilawatChapterData = convertChapterToTilawatChapterData
        }

    fun setCurrentReciter(reciter: ReciterWrapper) {
        tilawatChapterData.reciter = reciter
        postTilawatChapterLiveData()
    }

    private fun postTilawatChapterLiveData(data: TilawatChapterData = tilawatChapterData) = getTilawatChapterLiveData.postValue(data)

    private val convertChapterToTilawatChapterData
        get() = chapter.toTilawatChapterData()

    suspend fun getAudioData(number: Int, errorHandler: ErrorHandler) = tilawatRepository.getTilawatAudio(getAudioModel(number), errorHandler)

    private fun getAudioModel(number: Int) =
        TilawatAudioModel(getSurahYaseen, tilawatAudioClipRange.getMemberFromIndex(number), tilawatChapterData.reciterId).also {
            tilawatChapterData.currentVerseNumber = number
        }
}

val TilawatChapterProvider.authorData
    get() = tilawatChapterData.reciter?.reciter?.toAuthorData

val TilawatChapterProvider.surahName
    get() = tilawatChapterData.surahNameEnglish

val TilawatChapterProvider.number
    get() = tilawatChapterData.currentVerseNumber