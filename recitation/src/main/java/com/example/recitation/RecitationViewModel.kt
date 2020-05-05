package com.example.recitation

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.core.BaseViewModel
import com.example.data.Chapter
import com.example.network.error.ApiErrorType

class RecitationViewModel constructor(private val recitationChapterProvider: RecitationChapterProvider) : BaseViewModel() {

    val chapter: LiveData<Chapter> = liveData {
        recitationChapterProvider.getChapter(errorHandler = this@RecitationViewModel)?.let {
            emit(it)
        } ?: onError(ApiErrorType.UNKNOWN)
    }
}