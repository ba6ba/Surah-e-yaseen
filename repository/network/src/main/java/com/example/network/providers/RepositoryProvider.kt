package com.example.network.providers

import com.example.network.repository.ChapterNetworkRepository
import com.example.network.repository.RecitersNetworkRepository
import com.example.network.repository.AudioNetworkRepository
import com.example.network.services.Chapters
import com.example.network.services.Reciters
import com.example.network.services.AudioService
import retrofit2.Retrofit

fun chapterRepositoryProvider(retrofit: Retrofit) : ChapterNetworkRepository =
    ChapterNetworkRepository(retrofit.create(Chapters::class.java))

fun recitersRepositoryProvider(retrofit: Retrofit) : RecitersNetworkRepository =
    RecitersNetworkRepository(retrofit.create(Reciters::class.java))

fun tilawatRepositoryProvider(retrofit: Retrofit) : AudioNetworkRepository =
    AudioNetworkRepository(retrofit.create(AudioService::class.java))