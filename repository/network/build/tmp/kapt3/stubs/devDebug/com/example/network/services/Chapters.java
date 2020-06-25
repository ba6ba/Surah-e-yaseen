package com.example.network.services;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001JI\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00052\b\b\u0003\u0010\u0007\u001a\u00020\u00052\u000e\b\u0001\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\t2\b\b\u0003\u0010\n\u001a\u00020\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fJ\u001b\u0010\r\u001a\u00020\u000e2\b\b\u0001\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000f\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0010"}, d2 = {"Lcom/example/network/services/Chapters;", "", "fetchVerses", "Lcom/example/data/responses/VersesResponse;", "chapterNumber", "", "limit", "offset", "translations", "", "language", "", "(IIILjava/util/List;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "info", "Lcom/example/data/responses/ChapterResponse;", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "network_devDebug"})
public abstract interface Chapters {
    
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.GET(value = "chapters/{chapterNumber}/verses")
    public abstract java.lang.Object fetchVerses(@retrofit2.http.Path(value = "chapterNumber", encoded = true)
    int chapterNumber, @retrofit2.http.Query(value = "limit")
    int limit, @retrofit2.http.Query(value = "offset")
    int offset, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "translations[]")
    java.util.List<java.lang.Integer> translations, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "language")
    java.lang.String language, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.data.responses.VersesResponse> p5);
    
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.GET(value = "chapters/{chapterNumber}")
    public abstract java.lang.Object info(@retrofit2.http.Path(value = "chapterNumber", encoded = true)
    int chapterNumber, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.data.responses.ChapterResponse> p1);
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 3)
    public final class DefaultImpls {
    }
}