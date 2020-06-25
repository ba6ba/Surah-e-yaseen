package com.example.storage.di

import android.app.Application
import androidx.room.PrimaryKey
import androidx.room.Room
import com.example.storage.AppDatabase
import com.example.storage.AppDatabaseCallback
import com.example.storage.dao.AudioMediaDataDao
import com.example.storage.dao.SurahDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val storageModule = module {
    single { provideAppDatabase(androidApplication()) }
    single { provideSurahDao(get()) }
    single { provideAudioMediaDataDao(get()) }
}

fun provideAppDatabase(application: Application): AppDatabase =
    Room.databaseBuilder(application, AppDatabase::class.java, "Surah-e-yaseen.db")
        .build()

fun provideAudioMediaDataDao(appDatabase: AppDatabase): AudioMediaDataDao =
    appDatabase.audioMediaDataDao()

fun provideSurahDao(appDatabase: AppDatabase): SurahDao =
    appDatabase.surahDao()