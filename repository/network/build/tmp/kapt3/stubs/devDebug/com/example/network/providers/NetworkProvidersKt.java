package com.example.network.providers;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 2, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0006\u0010\u0000\u001a\u00020\u0001\u001a\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u0003\u001a\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005\u00a8\u0006\u000b"}, d2 = {"interceptorProvider", "Lokhttp3/Interceptor;", "loggingInterceptorProvider", "Lokhttp3/logging/HttpLoggingInterceptor;", "okHttpClientProvider", "Lokhttp3/OkHttpClient;", "interceptor", "loggingInterceptor", "retrofitBuilderProvider", "Lretrofit2/Retrofit;", "okHttpClient", "network_devDebug"})
public final class NetworkProvidersKt {
    
    @org.jetbrains.annotations.NotNull()
    public static final okhttp3.OkHttpClient okHttpClientProvider(@org.jetbrains.annotations.NotNull()
    okhttp3.Interceptor interceptor, @org.jetbrains.annotations.NotNull()
    okhttp3.logging.HttpLoggingInterceptor loggingInterceptor) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final okhttp3.Interceptor interceptorProvider() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final okhttp3.logging.HttpLoggingInterceptor loggingInterceptorProvider() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final retrofit2.Retrofit retrofitBuilderProvider(@org.jetbrains.annotations.NotNull()
    okhttp3.OkHttpClient okHttpClient) {
        return null;
    }
}