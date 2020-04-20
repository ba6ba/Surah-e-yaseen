package com.example.recitation.di

import com.example.recitation.RecitationItemsProvider
import org.koin.dsl.module

val recitationModule = module {
    factory { RecitationItemsProvider() }
}