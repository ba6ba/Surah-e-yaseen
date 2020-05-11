package com.example.network.di

import com.example.network.providers.chapterRepositoryProvider
import org.koin.dsl.module

val repositoryModule = module {
    factory { chapterRepositoryProvider(get()) }
}