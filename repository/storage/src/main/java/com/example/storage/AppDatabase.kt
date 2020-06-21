package com.example.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.audio.AudioMediaData
import com.example.storage.dao.AudioMediaDataDao
import com.example.storage.dao.SurahDao

@Database(version = 1, entities = [AudioMediaData::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun audioMediaDataDao(): AudioMediaDataDao
    abstract fun surahDao(): SurahDao
}