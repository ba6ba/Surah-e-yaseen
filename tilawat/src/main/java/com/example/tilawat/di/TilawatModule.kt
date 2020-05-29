package com.example.tilawat.di

import com.example.media.media.di.mediaModule
import com.example.tilawat.TilawatChapterProvider
import com.example.tilawat.TilawatViewModel
import com.example.reciters.translatorsModule
import org.koin.dsl.module

val tilawatModule = module {
    factory { TilawatViewModel(get(), get(), get()) }
    single { TilawatChapterProvider() }
} + translatorsModule + mediaModule