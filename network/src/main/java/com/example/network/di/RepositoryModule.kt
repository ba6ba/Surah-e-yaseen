package com.example.network.di

import com.example.network.providers.chapterRepositoryProvider
import com.example.network.providers.recitersRepositoryProvider
import com.example.network.providers.tilawatRepositoryProvider
import org.koin.dsl.module

val repositoryModule = module {
    factory { chapterRepositoryProvider(get()) }
    factory { recitersRepositoryProvider(get()) }
    factory { tilawatRepositoryProvider(get()) }
}