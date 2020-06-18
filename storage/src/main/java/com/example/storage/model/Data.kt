package com.example.storage.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data(
    var id: Int = INVALID_ID, var audioId: String = EMPTY_STRING,
    var totalNumber: Int = INVALID_PROGRESS.toInt()
) : Parcelable
