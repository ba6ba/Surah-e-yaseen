package com.example.network.services

import com.example.data.responses.ChapterResponse
import com.example.data.responses.VersesResponse
import com.example.network.LanguageProvider
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Chapters {
    @GET("chapters/{chapterNumber}/verses")
    suspend fun fetchVerses(@Path(value = "chapterNumber", encoded = true) chapterNumber : Int,
        @Query("limit") limit : Int = 1, @Query("offset") offset : Int = 0,
        @Query("translations[]") translations : List<Int>,
        @Query("language") language : String = LanguageProvider.getDefaultLanguageCode) : VersesResponse

    @GET("chapters/{chapterNumber}")
    suspend fun info(@Path(value = "chapterNumber", encoded = true) chapterNumber : Int) : ChapterResponse
}