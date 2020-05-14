package com.example.reciters

import org.koin.dsl.module

val translatorsModule = module {
    factory { RecitersProvider(get()) }
}