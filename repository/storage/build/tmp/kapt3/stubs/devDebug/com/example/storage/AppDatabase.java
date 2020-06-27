package com.example.storage;

import java.lang.System;

@androidx.room.TypeConverters(value = {com.example.storage.converters.DataConverter.class, com.example.storage.converters.ImageMetaDataConverter.class, com.example.storage.converters.AuthorDataConverter.class, com.example.storage.converters.MediaMetaDataConverter.class, com.example.storage.converters.MetaDataConverter.class, com.example.storage.converters.LastSavedAudioConverter.class, com.example.storage.converters.LastSavedReadingContentConverter.class})
@androidx.room.Database(version = 1, entities = {com.example.data.audio.AudioMediaData.class, com.example.data.lastsaved.LastSavedAudio.class, com.example.data.lastsaved.LastSavedReadingContent.class}, exportSchema = false)
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&\u00a8\u0006\t"}, d2 = {"Lcom/example/storage/AppDatabase;", "Landroidx/room/RoomDatabase;", "()V", "audioMediaDataDao", "Lcom/example/storage/dao/AudioMediaDataDao;", "lastSavedAudioDataDao", "Lcom/example/storage/dao/LastSavedAudioDataDao;", "surahDao", "Lcom/example/storage/dao/SurahDao;", "storage_devDebug"})
public abstract class AppDatabase extends androidx.room.RoomDatabase {
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.storage.dao.AudioMediaDataDao audioMediaDataDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.storage.dao.LastSavedAudioDataDao lastSavedAudioDataDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.storage.dao.SurahDao surahDao();
    
    public AppDatabase() {
        super();
    }
}