package com.example.data.audio

import androidx.annotation.DrawableRes
import java.io.Serializable

data class ServiceMetaData constructor(
    var id: Int = INVALID_ID,
    var url: String = EMPTY_STRING,
    @DrawableRes
    var imageRes : Int = INVALID_ID,
    var title: String = EMPTY_STRING,
    var audioId: String = EMPTY_STRING,
    var authorName: String = EMPTY_STRING,
    var audioDuration: Long = INVALID_PROGRESS
) : Serializable