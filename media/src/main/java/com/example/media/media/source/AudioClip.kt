package com.example.media.media.source

import com.example.data.Audio

data class AudioClip(
    var duration: Long,
    var title: String,
    var author: String,
    var format: String,
    var clipUrl: String,
    var imageUrl: String,
    var number: Long
) {

    companion object {
        fun from(audio: Audio, reciter: String, title: String, number: Long, imageUrl: String) =
            AudioClip(
                duration = audio.duration.toLong(),
                clipUrl = audio.url,
                format = audio.format,
                author = reciter,
                title = title,
                number = number,
                imageUrl = imageUrl
            )
    }
}