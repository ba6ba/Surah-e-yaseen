package com.example.network.services

import com.example.data.responses.RecitersResponse
import com.example.network.LanguageProvider
import retrofit2.http.GET
import retrofit2.http.Query

interface Reciters {

    @GET("options/recitations")
    suspend fun getReciters(@Query("language") language: String = LanguageProvider.getDefaultLanguageCode): RecitersResponse
}