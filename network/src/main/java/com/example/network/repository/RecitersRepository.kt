package com.example.network.repository

import com.example.network.error.ErrorHandler
import com.example.network.services.Reciters

class RecitersRepository(private val reciters: Reciters) : BaseRepository() {

    suspend fun getReciters(errorHandler: ErrorHandler) = makeApiCall(errorHandler) {
        reciters.getReciters()
    }
}