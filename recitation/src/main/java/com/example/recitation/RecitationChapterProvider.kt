package com.example.recitation

import com.example.data.Chapter
import com.example.data.verse.Verse
import com.example.extensions.*
import com.example.network.error.ErrorType
import com.example.network.error.ErrorHandler
import com.example.network.repository.ChapterNetworkRepository
import com.example.shared.Do
import com.example.shared.DoNothingLiveData
import com.example.shared.getSurahYaseen
import kotlin.math.ceil

class RecitationChapterProvider constructor(
    private val chapterNetworkRepository: ChapterNetworkRepository,
    val doNothingLiveData: DoNothingLiveData = DoNothingLiveData()
) {

    companion object {
        const val SAHIH_INTERNATION_EN = 20
        const val ABUL_ALA_MAUDUDI = 97
        const val LIMIT_MULTIPLIER = 2
    }

    private var currentPage: Int = 0
    private var rangeForPages: IntRange = 1..1
        set(value) {
            field = value
            numberOfPages = value.last
        }
    private var numberOfPages: Int = rangeForPages.last
    private var contentLimitForEachPage: Int = 1
    private val translationMetaData: List<Int> = listOf(
        SAHIH_INTERNATION_EN,
        ABUL_ALA_MAUDUDI
    )

    var chapter: Chapter? = null
        set(value) {
            field = value
            doNothingLiveData.postValue(Do.NOTHING)
        }

    suspend fun fetchChapterSpecificVerses(chapterNumber: Int = getSurahYaseen, offset: Int = 1): Verse? {
        val response = chapterNetworkRepository.getChapterVerses(chapterNumber, offset, 1, offset, translationMetaData, null)
        var verse: Verse? = null
        response?.verses?.hasData {
            verse = it.first()
        }
        return verse
    }

    suspend fun fetchChapterVerses(chapterNumber: Int = getSurahYaseen, errorHandler: ErrorHandler): Chapter? {
        val versesResponse = chapterNetworkRepository.getChapterVerses(
            chapterNumber, currentPage, contentLimitForEachPage.times(LIMIT_MULTIPLIER),
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

    fun setDataForFetchingChapterDetails(limitPerPage: Int = 20) {
        rangeForPages = 1..ceil(chapter?.numberOfVerses?.div(limitPerPage)?.toDouble() ?: 1.0).toInt()
        contentLimitForEachPage = limitPerPage
    }

    private fun emitEmptyDataError(errorHandler: ErrorHandler) {
        errorHandler.onError(ErrorType.NETWORK)
    }

    private fun increasePageNumber() {
        (currentPage in 0..numberOfPages).isTrue {
            currentPage = currentPage.plus(LIMIT_MULTIPLIER)
        }
    }

    fun doNeedToGetMoreVerses(pageNo: Int = 0) =
        (currentPage.greaterThanEqualsTo(pageNo)) && (chapter?.verses?.size ?: DEFAULT_ARRAY_SIZE).lessThan(
            chapter?.numberOfVerses ?: DEFAULT_INT_VALUE
        )
}