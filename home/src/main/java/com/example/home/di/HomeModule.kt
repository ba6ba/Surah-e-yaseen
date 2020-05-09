package com.example.home.di

import com.example.home.HomeViewModel
import com.example.recitation.ChapterProvider
import com.example.recitation.di.recitationModule
import com.example.tilawat.di.tilawatModule
import org.koin.dsl.module

val homeModule = module {
    single { ChapterProvider(get()) }
    factory { HomeViewModel(get()) }
} + tilawatModule + recitationModule