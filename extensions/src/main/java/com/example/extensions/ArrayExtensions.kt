package com.example.extensions

import android.graphics.Bitmap
import android.graphics.BitmapFactory

val ByteArray.getBitmap : Bitmap
    get() = BitmapFactory.decodeByteArray(this, 0, size)