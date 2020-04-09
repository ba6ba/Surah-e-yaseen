package com.example.extensions

import android.app.Activity
import android.view.View.SYSTEM_UI_FLAG_FULLSCREEN

fun Activity.enableFullScreenMode(hasFocus : Boolean) = kotlin.run {
    if (hasFocus)
        window.decorView.systemUiVisibility = SYSTEM_UI_FLAG_FULLSCREEN
}