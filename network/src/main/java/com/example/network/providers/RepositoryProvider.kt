package com.example.network.providers

import com.example.network.repository.ChapterRepository
import com.example.network.repository.RecitersRepository
import com.example.network.repository.TilawatRepository
import com.example.network.services.Chapters
import com.example.network.services.Reciters
import com.example.network.services.Tilawat
import retrofit2.Retrofit

fun chapterRepositoryProvider(retrofit: Retrofit) : ChapterRepository =
    ChapterRepository(retrofit.create(Chapters::class.java))

fun recitersRepositoryProvider(retrofit: Retrofit) : RecitersRepository =
    RecitersRepository(retrofit.create(Reciters::class.java))

fun tilawatRepositoryProvider(retrofit: Retrofit) : TilawatRepository =
    TilawatRepository(retrofit.create(Tilawat::class.java))