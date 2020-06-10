package com.example.network.repository

import com.example.data.TilawatAudioModel
import com.example.network.error.ErrorHandler
import com.example.network.services.Tilawat

class TilawatRepository(private val tilawat : Tilawat) : BaseRepository() {

    suspend fun getTilawatAudio(tilawatAudioModel: TilawatAudioModel, errorHandler: ErrorHandler) = makeApiCall(errorHandler) {
        tilawat.getAudio(tilawatAudioModel.chapterId, tilawatAudioModel.verseId, tilawatAudioModel.reciterId)
    }
}