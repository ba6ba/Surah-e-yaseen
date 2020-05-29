package com.example.data.responses

import com.example.data.Audio
import com.squareup.moshi.Json

data class TilawatAudioResponse(
    @field:Json(name = "audio_file")
    var audio : Audio? = null
)