package com.example.recitation

import androidx.lifecycle.MutableLiveData
import com.example.core.DO
import com.example.core.SURAH_E_YASEEN
import com.example.core.getChapterNumber
import com.example.data.Chapter
import com.example.data.responses.ChapterResponse
import com.example.extensions.*
import com.example.network.error.ApiErrorType
import com.example.network.error.ErrorHandler
import com.example.network.repository.ChapterRepository
import kotlin.math.ceil

class ChapterProvider constructor(private val chapterRepository: ChapterRepository) {

    companion object {
        const val SAHIH_INTERNATION_EN = 20
        const val ABUL_ALA_MAUDUDI = 97
        const val LIMIT_MULTILIER = 2
    }

    private var currentPage: Int = 0
    private var rangeForPages: IntRange = 1..1
        set(value) {
            field = value
            numberOfPages = value.last
        }
    private var numberOfPages: Int = rangeForPages.last
    private var contentLimitForEachPage: Int = 1
    var getVersesLiveData: MutableLiveData<DO> = MutableLiveData()
    private val translationMetaData: List<Int> = listOf(SAHIH_INTERNATION_EN, ABUL_ALA_MAUDUDI)
    private var chapterResponse: ChapterResponse? = null
    private var chapter: Chapter? = null

    suspend fun fetchChapterVerses(chapterNumber: Int = getChapterNumber(SURAH_E_YASEEN), errorHandler: ErrorHandler): Chapter? {
        val versesResponse = chapterRepository.getChapterVerses(
            chapterNumber, currentPage, contentLimitForEachPage.times(LIMIT_MULTILIER),
            chapter?.verses?.size ?: DEFAULT_ARRAY_SIZE, translationMetaData, errorHandler
        )
        return chapter?.apply {
            versesResponse?.verses.orEmpty().forEach {
                verses.add(it)
            }
            increasePageNumber()
        } ?: kotlin.run {
            emitEmptyDataError(errorHandler)
            null
        }
    }

    suspend fun fetchChapterInfo(chapterNumber: Int = getChapterNumber(SURAH_E_YASEEN), errorHandler: ErrorHandler) {
        chapterRepository.getChapterInfo(chapterNumber, errorHandler)?.also { response -> chapterResponse = response }?.apply {
            this@ChapterProvider.chapter = Chapter(this@apply.chapter)
            getVersesLiveData.postValue(DO.NOTHING)
        } ?: emitEmptyDataError(errorHandler)
    }

    fun setDataForFetchingChapterDetails(limitPerPage: Int = 20) {
        rangeForPages = 1..ceil(chapter?.numberOfVerses?.div(limitPerPage)?.toDouble() ?: 1.0).toInt()
        contentLimitForEachPage = limitPerPage
    }

    private fun emitEmptyDataError(errorHandler: ErrorHandler) {
        errorHandler.onError(ApiErrorType.NETWORK)
    }

    private fun increasePageNumber() {
        (currentPage in 0..numberOfPages).isTrue {
            currentPage = currentPage.plus(LIMIT_MULTILIER)
        }
    }

    fun doNeedToGetMoreVerses(pageNo: Int = 0) =
        (currentPage.greaterThanEqualsTo(pageNo)) && (chapter?.verses?.size ?: DEFAULT_ARRAY_SIZE).
            lessThan(chapter?.numberOfVerses ?: DEFAULT_INT_VALUE)
}