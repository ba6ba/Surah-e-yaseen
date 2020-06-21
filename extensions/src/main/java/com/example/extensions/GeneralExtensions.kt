package com.example.extensions

fun Any?.isNotNull() = this != null

inline fun Any?.isNotNull(noinline action : (Any.() -> Unit)? = null) = this?.let(action!!) ?: action

inline fun <T : Any> T?.nonNull(action : T.() -> Unit) = this?.apply { action(this) }

val <T : Any> T?.nonNull : Boolean
    get() = this != null
