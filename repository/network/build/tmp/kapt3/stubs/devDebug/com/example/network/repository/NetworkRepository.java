package com.example.network.repository;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0005\u00a2\u0006\u0002\u0010\u0002JC\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u00052$\b\u0004\u0010\u0006\u001a\u001e\b\u0001\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00050\t0\b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007H\u0086H\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\'\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\u0010\u000f\u001a\u00060\u0010j\u0002`\u0011H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012J\u0012\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0002JC\u0010\u0017\u001a\u0004\u0018\u0001H\u0005\"\u0004\b\u0000\u0010\u00052\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u001e\b\u0004\u0010\u0006\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00050\b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007H\u0086H\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0018\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001a"}, d2 = {"Lcom/example/network/repository/NetworkRepository;", "", "()V", "apiCall", "Lcom/example/network/ApiResponse;", "T", "apiFunction", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "Lretrofit2/Response;", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "errorHandling", "", "errorHandler", "Lcom/example/network/error/ErrorHandler;", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "(Lcom/example/network/error/ErrorHandler;Ljava/lang/Exception;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getErrorMessage", "", "response", "Lokhttp3/ResponseBody;", "makeApiCall", "(Lcom/example/network/error/ErrorHandler;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "network_devDebug"})
public abstract class NetworkRepository {
    private static final java.lang.String MESSAGE_KEY = "message";
    private static final java.lang.String ERROR_KEY = "error";
    public static final com.example.network.repository.NetworkRepository.Companion Companion = null;
    
    @org.jetbrains.annotations.Nullable()
    public final <T extends java.lang.Object>java.lang.Object apiCall(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super retrofit2.Response<T>>, ? extends java.lang.Object> apiFunction, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.network.ApiResponse<T>> p1) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final <T extends java.lang.Object>java.lang.Object makeApiCall(@org.jetbrains.annotations.Nullable()
    com.example.network.error.ErrorHandler errorHandler, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super T>, ? extends java.lang.Object> apiFunction, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super T> p2) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object errorHandling(@org.jetbrains.annotations.Nullable()
    com.example.network.error.ErrorHandler errorHandler, @org.jetbrains.annotations.NotNull()
    java.lang.Exception e, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p2) {
        return null;
    }
    
    private final java.lang.String getErrorMessage(okhttp3.ResponseBody response) {
        return null;
    }
    
    public NetworkRepository() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/example/network/repository/NetworkRepository$Companion;", "", "()V", "ERROR_KEY", "", "MESSAGE_KEY", "network_devDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}