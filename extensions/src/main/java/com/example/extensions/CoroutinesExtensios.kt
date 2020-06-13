package com.example.extensions

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.isActive

fun CoroutineScope.cancelIfActive() = isActive.isTrue { cancel() }