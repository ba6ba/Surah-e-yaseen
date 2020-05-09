package com.example.recitation

import androidx.lifecycle.*
import com.example.core.BaseViewModel
import com.example.data.Chapter
import com.example.extensions.checkForTrue
import com.example.extensions.isTrue
import com.example.network.error.ApiErrorType
import com.example.recitation.ui.RecitationsListPager

class RecitationViewModel constructor(private val chapterProvider: ChapterProvider) : BaseViewModel() {

    val canFetchVerses = chapterProvider.getVersesLiveData

    fun fetchVerses(recitationsListPager: RecitationsListPager, pageNo : Int = 0) = liveData{
        if (chapterProvider.doNeedToGetMoreVerses(pageNo = pageNo)) {
            chapterProvider.apply {
                setDataForFetchingChapterDetails(recitationsListPager.numberOfItemsPerPage)
                emit(fetchChapterVerses(errorHandler = this@RecitationViewModel))
            }
        }
    }
}