package com.example.storage.utils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0001\u000bB\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u00048F\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\u00048F\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\u0007\u00a8\u0006\f"}, d2 = {"Lcom/example/storage/utils/AppExecutors;", "", "()V", "diskExecutor", "Ljava/util/concurrent/Executor;", "diskIO", "getDiskIO", "()Ljava/util/concurrent/Executor;", "mainExecutor", "mainIO", "getMainIO", "MainThreadExecutor", "storage_devDebug"})
public final class AppExecutors {
    private static final java.util.concurrent.Executor diskExecutor = null;
    private static final java.util.concurrent.Executor mainExecutor = null;
    public static final com.example.storage.utils.AppExecutors INSTANCE = null;
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.concurrent.Executor getDiskIO() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.concurrent.Executor getMainIO() {
        return null;
    }
    
    private AppExecutors() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/example/storage/utils/AppExecutors$MainThreadExecutor;", "Ljava/util/concurrent/Executor;", "()V", "mainThreadExecutor", "Landroid/os/Handler;", "execute", "", "command", "Ljava/lang/Runnable;", "storage_devDebug"})
    static final class MainThreadExecutor implements java.util.concurrent.Executor {
        private final android.os.Handler mainThreadExecutor = null;
        
        @java.lang.Override()
        public void execute(@org.jetbrains.annotations.NotNull()
        java.lang.Runnable command) {
        }
        
        public MainThreadExecutor() {
            super();
        }
    }
}