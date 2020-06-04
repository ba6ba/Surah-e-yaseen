package com.example.data.audio

import androidx.annotation.DrawableRes
import java.io.Serializable

data class NotificationAudioWrapper(
    val audio: Audio, val author: String, val mainTitle: String, val number: Long, @DrawableRes val drawableId: Int
) : Serializable