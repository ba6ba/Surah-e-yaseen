package com.example.data.responses

import com.example.data.audio.Audio
import com.squareup.moshi.Json

data class AudioApiResponse(
    @field:Json(name = "audio_file")
    var audio: Audio? = null
)