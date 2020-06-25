package com.example.network.services;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J9\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u00052\b\b\u0003\u0010\u0007\u001a\u00020\u00052\b\b\u0003\u0010\b\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ?\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\f2\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u00052\b\b\u0003\u0010\u0007\u001a\u00020\u00052\b\b\u0003\u0010\b\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\r"}, d2 = {"Lcom/example/network/services/AudioService;", "", "getAudio", "Lcom/example/data/audio/AudioApiResponse;", "chapterNumber", "", "verseId", "reciterId", "language", "", "(IIILjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAudio1", "Lretrofit2/Response;", "network_devDebug"})
public abstract interface AudioService {
    
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.GET(value = "chapters/{chapterNumber}/verses/{verseId}/audio_files")
    public abstract java.lang.Object getAudio(@retrofit2.http.Path(value = "chapterNumber", encoded = true)
    int chapterNumber, @retrofit2.http.Path(value = "verseId", encoded = true)
    int verseId, @retrofit2.http.Query(value = "recitation")
    int reciterId, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "language")
    java.lang.String language, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.data.audio.AudioApiResponse> p4);
    
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.GET(value = "chapters/{chapterNumber}/verses/{verseId}/audio_files")
    public abstract java.lang.Object getAudio1(@retrofit2.http.Path(value = "chapterNumber", encoded = true)
    int chapterNumber, @retrofit2.http.Path(value = "verseId", encoded = true)
    int verseId, @retrofit2.http.Query(value = "recitation")
    int reciterId, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "language")
    java.lang.String language, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.data.audio.AudioApiResponse>> p4);
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 3)
    public final class DefaultImpls {
    }
}