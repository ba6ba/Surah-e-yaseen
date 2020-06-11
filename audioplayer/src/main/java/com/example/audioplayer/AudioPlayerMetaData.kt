package com.example.audioplayer

import android.graphics.Bitmap
import android.net.Uri

data class AudioPlayerMetaData (
    var title: String,
    var subTitle: String,
    var author: String,
    var playing : Boolean,
    var imageUri : Uri,
    var image : Bitmap?,
    var displayableDuration: String,
    var duration: Long
)