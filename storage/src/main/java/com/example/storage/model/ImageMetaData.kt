package com.example.storage.model

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImageMetaData(
    var imageUri: String = EMPTY_STRING,
    var imageDrawableRes: Int = INVALID_ID,
    var bitmap: Bitmap? = null
) : Parcelable