package com.example.extensions

import android.app.Activity
import android.util.DisplayMetrics
import android.view.View.SYSTEM_UI_FLAG_FULLSCREEN

fun Activity.enableFullScreenMode(hasFocus: Boolean) = kotlin.run {
    if (hasFocus)
        window.decorView.systemUiVisibility = SYSTEM_UI_FLAG_FULLSCREEN
}

val Activity.getWindowWidth
    get() = DisplayMetrics().apply {
        windowManager.defaultDisplay.getMetrics(this)
    }.widthPixels

val Activity.getWindowHeight
    get() = DisplayMetrics().apply {
        windowManager.defaultDisplay.getMetrics(this)
    }.heightPixels