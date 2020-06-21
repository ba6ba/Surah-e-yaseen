package com.example.home

import com.example.data.Chapter
import com.example.network.error.ErrorType
import com.example.network.error.ErrorHandler
import com.example.network.repository.ChapterNetworkRepository
import com.example.recitation.RecitationChapterProvider
import com.example.shared.getSurahYaseen
import com.example.tilawat.TilawatChapterProvider

class ChapterProvider constructor(
    private val chapterNetworkRepository: ChapterNetworkRepository,
    private val recitationChapterProvider: RecitationChapterProvider,
    private val tilawatChapterProvider: TilawatChapterProvider
) {

    suspend fun fetchChapterInfo(chapterNumber: Int = getSurahYaseen, errorHandler: ErrorHandler) {
        chapterNetworkRepository.getChapterInfo(chapterNumber, errorHandler)?.apply {
            val chapter = Chapter(chapter)
            recitationChapterProvider.chapter = chapter
            tilawatChapterProvider.chapter = chapter
        } ?: emitEmptyDataError(errorHandler)
    }

    private fun emitEmptyDataError(errorHandler: ErrorHandler) {
        errorHandler.onError(ErrorType.NETWORK)
    }

    suspend fun fetchChapterSpecificVerse(chapterNumber: Int = getSurahYaseen, numberOfVerses: Int = 0) =
        recitationChapterProvider.fetchChapterSpecificVerses(chapterNumber, numberOfVerses)?.also {
            tilawatChapterProvider.tilawatChapterData.firstVerseId = it.id ?: 0
        }
}