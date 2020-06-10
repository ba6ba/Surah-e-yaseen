package com.example.data.audio

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream

fun AudioMediaData.ImageMetaData.setBitmap(context: Context) = kotlin.run {
    bitmap = BitmapFactory.decodeResource(context.resources, imageDrawableRes)
}

fun AudioMediaData.Builder.setBitmap(context: Context) = apply {
    imageMetaData?.imageDrawableRes?.let {
        imageMetaData = imageMetaData?.copy(bitmap = BitmapFactory.decodeResource(context.resources, it))
    }
}.imageMetaData?.bitmap

val AudioMediaData.toServiceMetaData: AudioMediaData.ServiceMetaData
    get() = AudioMediaData.ServiceMetaData(
        data?.id!!,
        authorData?.name!!,
        metaData?.url!!,
        title = title,
        audioDuration = metaData?.audioDuration!!,
        byteArray = imageMetaData?.bitmap.toByteArray
    )

val Bitmap?.toByteArray: ByteArray
    get() = ByteArrayOutputStream().apply { this@toByteArray?.compress(Bitmap.CompressFormat.PNG, 50, this) }.toByteArray()