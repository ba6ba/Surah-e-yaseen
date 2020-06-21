package com.example.network.repository

import com.example.network.error.ErrorHandler
import com.example.network.services.Chapters

class ChapterNetworkRepository(private val chapters: Chapters) : NetworkRepository() {

    suspend fun getChapterInfo(chapterNumber: Int, errorHandler: ErrorHandler) =
        makeApiCall(errorHandler) {
            chapters.info(chapterNumber = chapterNumber)
        }

    suspend fun getChapterVerses(chapterNumber: Int, page: Int, limit: Int, offset: Int, translations: List<Int>, errorHandler: ErrorHandler?) =
        makeApiCall(errorHandler) {
            chapters.fetchVerses(chapterNumber = chapterNumber, limit = limit, offset = offset, translations = translations)
        }
}