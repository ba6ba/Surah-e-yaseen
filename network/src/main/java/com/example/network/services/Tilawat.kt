package com.example.network.services

import com.example.data.responses.AudioApiResponse
import com.example.network.LanguageProvider
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Tilawat {

    @GET("chapters/{chapterNumber}/verses/{verseId}/audio_files")
    suspend fun getAudio(@Path(value = "chapterNumber", encoded = true) chapterNumber : Int,
        @Path(value = "verseId", encoded = true) verseId : Int, @Query("recitation") reciterId : Int = 7,
        @Query("language") language : String = LanguageProvider.getDefaultLanguageCode) : AudioApiResponse

}