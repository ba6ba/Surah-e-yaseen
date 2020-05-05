package com.example.recitation

import com.example.core.SURAH_E_YASEEN
import com.example.core.getChapterNumber
import com.example.data.Chapter
import com.example.data.get
import com.example.data.responses.ChapterResponse
import com.example.network.error.ErrorHandler
import com.example.network.repository.ChapterRepository

class RecitationChapterProvider constructor(private val chapterRepository: ChapterRepository) {

    var currentPage: Int = 1
    var apiTotalNumberOfPages: Int = 1
    var pagerTotalNumberOfPages: Int = 1
    private var chapterResponse: ChapterResponse? = null

    suspend fun getChapter(chapterNumber: Int = getChapterNumber(SURAH_E_YASEEN), errorHandler: ErrorHandler): Chapter? {
        if (chapterResponse == null)
            chapterResponse = chapterRepository.getChapterInfo(chapterNumber, errorHandler)
        val versesResponse = chapterRepository.getChapterVerses(chapterNumber, currentPage, errorHandler)
        return Chapter(chapterResponse?.chapter, versesResponse?.verses).get()
    }
}