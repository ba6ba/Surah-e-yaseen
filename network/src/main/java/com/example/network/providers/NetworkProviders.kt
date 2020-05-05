package com.example.network.providers

import com.example.network.BuildConfig
import com.example.network.readAndWriteTimeOut
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

fun okHttpClientProvider(interceptor: Interceptor, loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
    OkHttpClient().newBuilder().addInterceptor(interceptor).addInterceptor(loggingInterceptor).readAndWriteTimeOut.build()

fun interceptorProvider(): Interceptor = object : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        // add custom headers if there's any else remove this interceptor
        return chain.proceed(chain.request())
    }
}

fun loggingInterceptorProvider(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

fun retrofitBuilderProvider(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).client(okHttpClient).addConverterFactory(MoshiConverterFactory.create()).build()