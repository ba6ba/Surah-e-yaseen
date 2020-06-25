package com.example.network.repository;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J#\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJK\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\b0\u00122\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0014"}, d2 = {"Lcom/example/network/repository/ChapterNetworkRepository;", "Lcom/example/network/repository/NetworkRepository;", "chapters", "Lcom/example/network/services/Chapters;", "(Lcom/example/network/services/Chapters;)V", "getChapterInfo", "Lcom/example/data/responses/ChapterResponse;", "chapterNumber", "", "errorHandler", "Lcom/example/network/error/ErrorHandler;", "(ILcom/example/network/error/ErrorHandler;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getChapterVerses", "Lcom/example/data/responses/VersesResponse;", "page", "limit", "offset", "translations", "", "(IIIILjava/util/List;Lcom/example/network/error/ErrorHandler;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "network_devDebug"})
public final class ChapterNetworkRepository extends com.example.network.repository.NetworkRepository {
    private final com.example.network.services.Chapters chapters = null;
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getChapterInfo(int chapterNumber, @org.jetbrains.annotations.NotNull()
    com.example.network.error.ErrorHandler errorHandler, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.data.responses.ChapterResponse> p2) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getChapterVerses(int chapterNumber, int page, int limit, int offset, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Integer> translations, @org.jetbrains.annotations.Nullable()
    com.example.network.error.ErrorHandler errorHandler, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.data.responses.VersesResponse> p6) {
        return null;
    }
    
    public ChapterNetworkRepository(@org.jetbrains.annotations.NotNull()
    com.example.network.services.Chapters chapters) {
        super();
    }
}