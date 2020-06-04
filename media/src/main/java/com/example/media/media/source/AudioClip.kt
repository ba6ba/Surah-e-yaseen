package com.example.media.media.source

import android.graphics.Bitmap
import com.example.data.audio.Audio

data class AudioClip(
    var duration: Long,
    var title: String,
    var author: String,
    var format: String,
    var clipUrl: String,
    var imageUrl: String,
    var number: Long,
    var imageBitmap : Bitmap?
) {

    companion object {
        fun from(audio: Audio, reciter: String, title: String, number: Long, imageUrl: String, imageBitmap: Bitmap?) =
            AudioClip(
                duration = audio.duration.toLong(),
                clipUrl = audio.url,
                format = audio.format,
                author = reciter,
                title = title,
                number = number,
                imageUrl = imageUrl,
                imageBitmap = imageBitmap
            )
    }
}