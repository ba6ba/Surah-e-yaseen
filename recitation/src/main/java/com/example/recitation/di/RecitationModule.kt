package com.example.recitation.di

import com.example.recitation.RecitationViewModel
import org.koin.dsl.module

val recitationModule = module {
    factory { RecitationViewModel(get()) }
}