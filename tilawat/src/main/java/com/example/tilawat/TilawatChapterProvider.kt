package com.example.tilawat

import androidx.lifecycle.MutableLiveData
import com.example.data.Chapter
import com.example.data.reciters.ReciterWrapper

class TilawatChapterProvider {

    var getTilawatChapterLiveData: MutableLiveData<TilawatChapterData> = MutableLiveData()
    private var tilawatChapterData: TilawatChapterData = TilawatChapterData()

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
}