package com.example.network.repository

import com.example.network.error.ErrorHandler
import com.example.network.services.Reciters

class RecitersNetworkRepository(private val reciters: Reciters) : NetworkRepository() {

    suspend fun getReciters(errorHandler: ErrorHandler) = makeApiCall(errorHandler) {
        reciters.getReciters()
    }
}