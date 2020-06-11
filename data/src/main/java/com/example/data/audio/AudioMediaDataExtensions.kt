package com.example.data.audio

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream

fun AudioMediaData.ImageMetaData.setBitmap(context: Context) = kotlin.run {
    bitmap = BitmapFactory.decodeResource(context.resources, imageDrawableRes)
}

val AudioMediaData.toServiceMetaData: AudioMediaData.ServiceMetaData
    get() = AudioMediaData.ServiceMetaData(
        data?.id!!,
        authorData?.name!!,
        metaData?.url!!,
        title = title,
        audioDuration = metaData?.audioDuration!!,
        byteArray = imageMetaData?.bitmap.toByteArray,
        audioId = data?.audioId!!
    )

val Bitmap?.toByteArray: ByteArray
    get() = ByteArrayOutputStream().apply { this@toByteArray?.compress(Bitmap.CompressFormat.PNG, 50, this) }.toByteArray()