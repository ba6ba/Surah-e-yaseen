package com.example.data.lastsaved

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class LastSavedData(
    var lastSavedAudio: LastSavedAudio,
    var lastSavedReadingContent: LastSavedReadingContent
) : Parcelable