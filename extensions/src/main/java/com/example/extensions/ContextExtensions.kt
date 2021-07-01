package com.example.extensions

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.annotation.DrawableRes

fun Context.generateBitmap(@DrawableRes imageDrawableRes : Int) : Bitmap =
    BitmapFactory.decodeResource(resources, imageDrawableRes)