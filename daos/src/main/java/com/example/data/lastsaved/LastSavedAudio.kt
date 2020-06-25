package com.example.data.lastsaved

import android.os.Parcelable
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LastSavedAudio(
    @PrimaryKey(autoGenerate = false) var audioIndex : Int,
    @PrimaryKey(autoGenerate = false) var audioId : String
) : Parcelable