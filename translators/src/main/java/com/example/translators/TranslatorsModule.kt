package com.example.translators

import org.koin.dsl.module

val translatorsModule = module {
    factory { TranslatorsProvider() }
}