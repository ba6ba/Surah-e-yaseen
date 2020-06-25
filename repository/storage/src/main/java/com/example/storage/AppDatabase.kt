package com.example.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.audio.AudioMediaData
import com.example.data.lastsaved.LastSavedData
import com.example.storage.converters.*
import com.example.storage.dao.AudioMediaDataDao
import com.example.storage.dao.SurahDao

@Database(version = 1, entities = [AudioMediaData::class, LastSavedData::class], exportSchema = false)
@TypeConverters(
    value = [(DataConverter::class), (ImageMetaDataConverter::class),
        (AuthorDataConverter::class), (MediaMetaDataConverter::class), (MetaDataConverter::class),
        (LastSavedAudioConverter::class), (LastSavedReadingContentConverter::class)]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun audioMediaDataDao(): AudioMediaDataDao
    abstract fun surahDao(): SurahDao
}