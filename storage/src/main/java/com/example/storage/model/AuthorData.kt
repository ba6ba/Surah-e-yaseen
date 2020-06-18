package com.example.storage.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AuthorData constructor(
    var id: Int = INVALID_ID,
    var name: String = EMPTY_STRING
) : Parcelable