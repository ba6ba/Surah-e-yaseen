package com.example.data.lastsaved

import android.os.Parcelable
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LastSavedReadingContent(
    @PrimaryKey(autoGenerate = false) var id : Int,
    var pageNumber : Int
) : Parcelable