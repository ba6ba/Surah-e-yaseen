package com.example.network.providers

import com.example.network.repository.ChapterRepository
import com.example.network.services.Chapters
import retrofit2.Retrofit

fun chapterRepositoryProvider(retrofit: Retrofit) : ChapterRepository =
    ChapterRepository(retrofit.create(Chapters::class.java))