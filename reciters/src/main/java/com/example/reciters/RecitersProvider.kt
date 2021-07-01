package com.example.reciters

import com.example.network.error.ErrorHandler
import com.example.network.repository.RecitersNetworkRepository

class RecitersProvider(private val recitersNetworkRepository: RecitersNetworkRepository) {

    suspend fun getReciters(errorHandler: ErrorHandler) =
        recitersNetworkRepository.getReciters(errorHandler = errorHandler)
}
