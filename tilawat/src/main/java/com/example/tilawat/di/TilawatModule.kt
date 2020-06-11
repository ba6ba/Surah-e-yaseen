package com.example.tilawat.di

import com.example.media.media.di.mediaModule
import com.example.tilawat.TilawatChapterProvider
import com.example.tilawat.TilawatViewModel
import com.example.reciters.translatorsModule
import com.example.tilawat.dataprovider.AudioDataProvider
import com.example.tilawat.dataprovider.IAudioData
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.bind
import org.koin.dsl.module

val tilawatModule = module {
    factory { TilawatViewModel(androidContext(),get(), get(), get(), get()) }
    single { TilawatChapterProvider(get()) }
    factory { AudioDataProvider() } bind IAudioData::class
} + translatorsModule + mediaModule