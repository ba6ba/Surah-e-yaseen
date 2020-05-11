package com.example.extensions

import android.util.DisplayMetrics
import android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.enableFullScreenMode(hasFocus: Boolean) = kotlin.run {
    if (hasFocus)
        window.decorView.systemUiVisibility = SYSTEM_UI_FLAG_FULLSCREEN
}

val AppCompatActivity.getWindowWidth
    get() = DisplayMetrics().apply {
        windowManager.defaultDisplay.getMetrics(this)
    }.widthPixels

val AppCompatActivity.getWindowHeight
    get() = DisplayMetrics().apply {
        windowManager.defaultDisplay.getMetrics(this)
    }.heightPixels

fun AppCompatActivity.showToast(message : String, length : Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, message, length).show()