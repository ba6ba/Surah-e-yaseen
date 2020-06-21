package com.example.repository.di

import com.example.repository.AudioDataRepository
import com.example.repository.providers.AudioDataProvider
import org.koin.dsl.module

val repositoryModule = module {
    factory { AudioDataProvider() }
    single { AudioDataRepository(get(), get(), get()) }
}