package com.example.network.di

import com.example.network.providers.interceptorProvider
import com.example.network.providers.loggingInterceptorProvider
import com.example.network.providers.okHttpClientProvider
import com.example.network.providers.retrofitBuilderProvider
import org.koin.dsl.module

val networkUtilsModule = module {
    factory { loggingInterceptorProvider() }
    factory { interceptorProvider() }
    factory { okHttpClientProvider(get(), get()) }
    single { retrofitBuilderProvider(get()) }
}