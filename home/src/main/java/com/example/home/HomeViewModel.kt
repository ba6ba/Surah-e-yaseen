package com.example.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.core.BaseViewModel
import com.example.data.verse.Verse
import kotlinx.coroutines.launch

class HomeViewModel constructor(private val chapterProvider: ChapterProvider) : BaseViewModel() {

    private suspend fun fetchChapterInfo() = chapterProvider.fetchChapterInfo(errorHandler = this)

    private suspend fun fetchSpecificChapterVerse() = chapterProvider.fetchChapterSpecificVerse()

    fun fetchTilawatData() : LiveData<Unit> = liveData {
        fetchChapterInfo()
        fetchSpecificChapterVerse()
    }
}