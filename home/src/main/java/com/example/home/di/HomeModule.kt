package com.example.home.di

import com.example.home.ChapterProvider
import com.example.home.HomeViewModel
import com.example.recitation.di.recitationModule
import com.example.storage.di.storageModule
import com.example.tilawat.di.tilawatModule
import org.koin.dsl.module

val homeModule = module {
    factory { HomeViewModel(get()) }
    factory { ChapterProvider(get(), get(), get()) }
} + tilawatModule + recitationModule + storageModule