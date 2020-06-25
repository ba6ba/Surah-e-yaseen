package com.example.data.audio

import com.squareup.moshi.Json

data class AudioApiResponse(
    @field:Json(name = "audio_file")
    var audio: Audio? = null
)