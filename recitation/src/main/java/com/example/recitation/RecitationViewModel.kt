package com.example.recitation

import androidx.lifecycle.*
import com.example.core.BaseViewModel
import com.example.recitation.ui.RecitationsListPager

class RecitationViewModel constructor(private val recitationChapterProvider: RecitationChapterProvider) : BaseViewModel() {

    val canFetchVerses = recitationChapterProvider.doNothingLiveData

    fun fetchVerses(recitationsListPager: RecitationsListPager, pageNo : Int = 0) = liveData{
        if (recitationChapterProvider.doNeedToGetMoreVerses(pageNo = pageNo)) {
            recitationChapterProvider.apply {
                setDataForFetchingChapterDetails(recitationsListPager.numberOfItemsPerPage)
                emit(fetchChapterVerses(errorHandler = this@RecitationViewModel))
            }
        }
    }
}