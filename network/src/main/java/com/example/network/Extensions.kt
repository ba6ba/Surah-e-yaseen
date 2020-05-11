package com.example.network

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

const val NETWORK_TIMEOUT = 60L

val OkHttpClient.Builder.readAndWriteTimeOut: OkHttpClient.Builder
    get() {
        readTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
        writeTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
        return this
    }