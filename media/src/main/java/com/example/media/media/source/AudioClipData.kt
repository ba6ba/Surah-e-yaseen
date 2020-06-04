package com.example.media.media.source

import android.graphics.Bitmap
import android.net.Uri

data class AudioClipData(
    val mediaId: String,
    val title: String,
    val subtitle: String,
    val albumArtUri: Uri,
    val browsable: Boolean,
    var playing: Boolean,
    var mediaUri : Uri?
) {

    data class Metadata (
        var title: String,
        var subTitle: String,
        var author: String,
        var playing: Boolean,
        var imageUri : Uri,
        var image : Bitmap?,
        var displayableDuration: String,
        var duration: Long
    )
}