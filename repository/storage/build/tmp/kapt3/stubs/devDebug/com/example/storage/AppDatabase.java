package com.example.storage;

import java.lang.System;

@androidx.room.TypeConverters(value = {com.example.storage.converters.DataConverter.class, com.example.storage.converters.ImageMetaDataConverter.class, com.example.storage.converters.AuthorDataConverter.class, com.example.storage.converters.MediaMetaDataConverter.class, com.example.storage.converters.MetaDataConverter.class})
@androidx.room.Database(version = 1, entities = {com.example.data.audio.AudioMediaData.class}, exportSchema = false)
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&\u00a8\u0006\u0007"}, d2 = {"Lcom/example/storage/AppDatabase;", "Landroidx/room/RoomDatabase;", "()V", "audioMediaDataDao", "Lcom/example/storage/dao/AudioMediaDataDao;", "surahDao", "Lcom/example/storage/dao/SurahDao;", "storage_devDebug"})
public abstract class AppDatabase extends androidx.room.RoomDatabase {
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.storage.dao.AudioMediaDataDao audioMediaDataDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.storage.dao.SurahDao surahDao();
    
    public AppDatabase() {
        super();
    }
}