package com.example.data.audio

import androidx.annotation.DrawableRes
import java.io.Serializable

data class Audio(
    var url: String,
    var duration: Int,
    var segments: List<List<Int>> = emptyList(),
    var format: String
) : Serializable

fun Audio.toNotificationAudioWrapper(author : String, mainTitle : String, number : Long, @DrawableRes drawableId : Int) =
    NotificationAudioWrapper(this, author, mainTitle,number, drawableId)