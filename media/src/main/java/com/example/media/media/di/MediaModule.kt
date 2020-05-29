package com.example.media.media.di

import android.content.ComponentName
import com.example.media.media.AudioServiceConnection
import com.example.media.media.service.AudioService
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val mediaModule = module {
    single { AudioServiceConnection(androidContext(),
        ComponentName(androidContext(), AudioService::class.java)) }
}