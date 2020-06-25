package com.example.network.repository;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J#\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u001f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\r2\u0006\u0010\u0007\u001a\u00020\bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000f"}, d2 = {"Lcom/example/network/repository/AudioNetworkRepository;", "Lcom/example/network/repository/NetworkRepository;", "audioService", "Lcom/example/network/services/AudioService;", "(Lcom/example/network/services/AudioService;)V", "getAudioClipData", "Lcom/example/data/audio/AudioApiResponse;", "audioClipModel", "Lcom/example/data/audio/AudioClipModel;", "errorHandler", "Lcom/example/network/error/ErrorHandler;", "(Lcom/example/data/audio/AudioClipModel;Lcom/example/network/error/ErrorHandler;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAudioClipData1", "Lcom/example/network/ApiResponse;", "(Lcom/example/data/audio/AudioClipModel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "network_devDebug"})
public final class AudioNetworkRepository extends com.example.network.repository.NetworkRepository {
    private final com.example.network.services.AudioService audioService = null;
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getAudioClipData(@org.jetbrains.annotations.NotNull()
    com.example.data.audio.AudioClipModel audioClipModel, @org.jetbrains.annotations.NotNull()
    com.example.network.error.ErrorHandler errorHandler, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.data.audio.AudioApiResponse> p2) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getAudioClipData1(@org.jetbrains.annotations.NotNull()
    com.example.data.audio.AudioClipModel audioClipModel, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.network.ApiResponse<com.example.data.audio.AudioApiResponse>> p1) {
        return null;
    }
    
    public AudioNetworkRepository(@org.jetbrains.annotations.NotNull()
    com.example.network.services.AudioService audioService) {
        super();
    }
}