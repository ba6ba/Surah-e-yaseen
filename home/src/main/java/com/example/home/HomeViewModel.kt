package com.example.home

import androidx.lifecycle.liveData
import com.example.core.BaseViewModel

class HomeViewModel constructor(private val chapterProvider: ChapterProvider): BaseViewModel() {

    fun fetchChapterInfo() = liveData<Unit> {
        chapterProvider.fetchChapterInfo(errorHandler = this@HomeViewModel)
    }
}