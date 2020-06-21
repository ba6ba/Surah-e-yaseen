package com.example.storage.utils

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors

object AppExecutors {

    private val diskExecutor : Executor = Executors.newSingleThreadExecutor()
    private val mainExecutor : Executor = MainThreadExecutor()

    val diskIO
        get() = diskExecutor

    val mainIO
        get() = mainExecutor

    private class MainThreadExecutor : Executor {
        private val mainThreadExecutor = Handler(Looper.getMainLooper())
        override fun execute(command: Runnable) {
            mainThreadExecutor.post(command)
        }

    }
}