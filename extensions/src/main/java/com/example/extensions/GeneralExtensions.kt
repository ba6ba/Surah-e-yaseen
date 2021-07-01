package com.example.extensions

import android.content.Context
import android.graphics.Bitmap
import androidx.annotation.DrawableRes

inline fun <T> T?.nonNull(action: T.() -> Unit) = this?.apply { action(this) }

val <T : Any> T?.nonNull: Boolean
    get() = this != null

inline fun <R> isTrue(condition: Boolean, crossinline block: () -> R?) = if (condition) block() else null

inline fun <R> isFalse(condition: Boolean, crossinline block: () -> R?) = if (condition.not()) block() else null

inline fun <R> isNull(condition: Any?, crossinline block: () -> R?) = if (condition == null) block() else null

inline fun <R> notNull(condition: Any?, crossinline block: () -> R?) = if (condition != null) block() else null

fun generateBitmap(context: Context, @DrawableRes imageDrawableRes: Int): Bitmap =
    context.generateBitmap(imageDrawableRes)