package com.example.tilawat.di

import com.example.media.media.di.mediaModule
import com.example.reciters.translatorsModule
import com.example.repository.di.repositoryModule
import com.example.tilawat.TilawatChapterProvider
import com.example.tilawat.TilawatViewModel
import com.example.tilawat.dataprovider.AudioDataProvider
import com.example.tilawat.dataprovider.IAudioData
import org.koin.dsl.bind
import org.koin.dsl.module

val tilawatModule = module {
    factory { TilawatViewModel(get(), get(), get(), get(), get(), get()) }
    single { TilawatChapterProvider() }
    factory { AudioDataProvider() } bind IAudioData::class
} + translatorsModule + mediaModule + repositoryModule