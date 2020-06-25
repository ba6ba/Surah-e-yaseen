package com.example.data.lastsaved

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class LastSavedData(
    @PrimaryKey(autoGenerate = true) var id : Int,
    var lastSavedAudio: LastSavedAudio,
    var lastSavedReadingContent: LastSavedReadingContent
) : Parcelable