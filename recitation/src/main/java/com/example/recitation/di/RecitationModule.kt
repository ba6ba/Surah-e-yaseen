package com.example.recitation.di

import com.example.recitation.RecitationChapterProvider
import com.example.recitation.RecitationViewModel
import org.koin.dsl.module

val recitationModule = module {
    single { RecitationChapterProvider(get()) }
    factory { RecitationViewModel(get()) }
}