package com.example.network

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

val IODispatcher : CoroutineDispatcher
    get() = Dispatchers.IO

val MainDispatcher : CoroutineDispatcher
    get() = Dispatchers.Main

val DefaultDispatcher : CoroutineDispatcher
    get() = Dispatchers.Default