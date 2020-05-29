package com.example.network.repository

import com.example.network.error.ErrorHandler
import com.example.network.services.Tilawat

class TilawatRepository(private val tilawat : Tilawat) : BaseRepository() {

    suspend fun getTilawatAudio(chapterId : Int, verseId : Int, reciterId : Int, errorHandler: ErrorHandler?) = makeApiCall(errorHandler) {
        tilawat.getAudio(chapterId, verseId, reciterId)
    }
}