package com.example.repository.di

import com.example.repository.AudioMediaDataRepository
import com.example.repository.dataprovider.AudioMediaDataMapper
import com.example.repository.dataprovider.IAudioMediaDataMapper
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    single { AudioMediaDataRepository(get(), get(), get()) }
    factory { AudioMediaDataMapper() } bind IAudioMediaDataMapper::class
}