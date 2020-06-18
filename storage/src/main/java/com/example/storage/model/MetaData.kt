package com.example.storage.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MetaData(
    var url: String = EMPTY_STRING,
    var number: Long = INVALID_PROGRESS,
    var audioDuration: Long = INVALID_PROGRESS,
    var format: String = EMPTY_STRING
) : Parcelable