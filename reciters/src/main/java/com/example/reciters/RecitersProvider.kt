package com.example.reciters

import com.example.network.error.ErrorHandler
import com.example.network.repository.RecitersRepository

class RecitersProvider(private val recitersRepository: RecitersRepository) {

    suspend fun getReciters(errorHandler: ErrorHandler) =
        recitersRepository.getReciters(errorHandler = errorHandler)
}
