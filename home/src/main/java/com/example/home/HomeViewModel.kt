package com.example.home

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.liveData
import com.example.core.BaseViewModel
import com.example.data.verse.Verse

class HomeViewModel constructor(private val chapterProvider: ChapterProvider): BaseViewModel() {

    private fun fetchChapterInfo() = liveData<Unit> {
        chapterProvider.fetchChapterInfo(errorHandler = this@HomeViewModel)
    }

    private fun fetchSpecificChapterVerse() = liveData<Verse> {
        chapterProvider.fetchChapterSpecificVerse()
    }

    fun fetchTilawatData() = MediatorLiveData<Unit>().apply {
        addSource(fetchSpecificChapterVerse()) {
            addSource(fetchChapterInfo()) {
                //
            }
        }
    }
}