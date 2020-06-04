package com.example.data.responses

import androidx.annotation.DrawableRes
import com.example.data.audio.Audio
import com.example.data.audio.NotificationAudioWrapper
import com.example.data.audio.toNotificationAudioWrapper
import com.squareup.moshi.Json

data class TilawatAudioResponse(
    @field:Json(name = "audio_file")
    var audio: Audio? = null
)

fun TilawatAudioResponse.toNotificationAudioWrapper(author: String, mainTitle: String, number: Long, @DrawableRes drawableId: Int):
        NotificationAudioWrapper? = if (audio != null) audio?.toNotificationAudioWrapper(author, mainTitle, number, drawableId) else null