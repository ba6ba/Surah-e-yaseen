package com.example.storage.di;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 2, d1 = {"\u0000*\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007\u001a\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005\u001a\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\n\u001a\u00020\u0005\u001a\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u0005\"\u0011\u0010\u0000\u001a\u00020\u0001\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\u00a8\u0006\u000f"}, d2 = {"storageModule", "Lorg/koin/core/module/Module;", "getStorageModule", "()Lorg/koin/core/module/Module;", "provideAppDatabase", "Lcom/example/storage/AppDatabase;", "application", "Landroid/app/Application;", "provideAudioMediaDataDao", "Lcom/example/storage/dao/AudioMediaDataDao;", "appDatabase", "provideLastSavedAudioDataDao", "Lcom/example/storage/dao/LastSavedAudioDataDao;", "provideSurahDao", "Lcom/example/storage/dao/SurahDao;", "storage_devDebug"})
public final class StorageModuleKt {
    @org.jetbrains.annotations.NotNull()
    private static final org.koin.core.module.Module storageModule = null;
    
    @org.jetbrains.annotations.NotNull()
    public static final org.koin.core.module.Module getStorageModule() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final com.example.storage.AppDatabase provideAppDatabase(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final com.example.storage.dao.AudioMediaDataDao provideAudioMediaDataDao(@org.jetbrains.annotations.NotNull()
    com.example.storage.AppDatabase appDatabase) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final com.example.storage.dao.SurahDao provideSurahDao(@org.jetbrains.annotations.NotNull()
    com.example.storage.AppDatabase appDatabase) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final com.example.storage.dao.LastSavedAudioDataDao provideLastSavedAudioDataDao(@org.jetbrains.annotations.NotNull()
    com.example.storage.AppDatabase appDatabase) {
        return null;
    }
}