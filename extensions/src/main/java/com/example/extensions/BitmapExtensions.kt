package com.example.extensions

import android.graphics.Bitmap
import java.io.ByteArrayOutputStream

val Bitmap?.toByteArray: ByteArray
    get() = ByteArrayOutputStream().apply { this@toByteArray?.compress(Bitmap.CompressFormat.PNG, 50, this) }.toByteArray()