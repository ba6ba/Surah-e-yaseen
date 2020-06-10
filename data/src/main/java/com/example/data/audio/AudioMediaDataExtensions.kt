package com.example.data.audio

import android.content.Context
import android.graphics.BitmapFactory

fun AudioMediaData.setBitmap(context: Context) = apply {
    imageMetaData?.imageDrawableRes?.let {
        imageMetaData = imageMetaData?.copy(bitmap = BitmapFactory.decodeResource(context.resources, it))
    }
}.imageMetaData?.bitmap

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
        bitmap = imageMetaData?.bitmap
    )