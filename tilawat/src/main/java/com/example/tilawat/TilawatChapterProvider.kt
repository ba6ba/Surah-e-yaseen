package com.example.tilawat

import androidx.lifecycle.MutableLiveData
import com.example.core.SURAH_E_YASEEN
import com.example.core.getChapterNumber
import com.example.data.Chapter
import com.example.data.reciters.ReciterWrapper
import com.example.network.repository.TilawatRepository

class TilawatChapterProvider(private val tilawatRepository: TilawatRepository) {

    var getTilawatChapterLiveData: MutableLiveData<TilawatChapterData> = MutableLiveData()
    var tilawatChapterData: TilawatChapterData = TilawatChapterData()
    private val tilawatAudioClipRange: IntRange by lazy {
        tilawatChapterData.firstVerseId..tilawatChapterData.firstVerseId.plus(tilawatChapterData.numberOfVerses - 1)
    }

    var chapter: Chapter? = null
        set(value) {
            field = value
            postTilawatChapterLiveData(tilawatChapterDataFromChapter)
        }

    fun setCurrentReciter(reciter: ReciterWrapper) {
        tilawatChapterData.reciter = reciter
        postTilawatChapterLiveData()
    }

    private fun postTilawatChapterLiveData(data: TilawatChapterData = tilawatChapterData) = getTilawatChapterLiveData.postValue(data)

    private val tilawatChapterDataFromChapter
        get() = chapter.toTilawatChapterData().also { tilawatChapterData = it }

    suspend fun getAudio(number: Int) =
        tilawatRepository.getTilawatAudio(
            getChapterNumber(SURAH_E_YASEEN), tilawatAudioClipRange.toList()[number],
            tilawatChapterData.reciter?.reciter?.id ?: 7, null
        )
}