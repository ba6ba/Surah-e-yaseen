package com.example.data.lastsaved

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

private const val LAST_PLAYED_AUDIO = "lastPlayedAudio"

@Entity
@Parcelize
data class LastSavedAudio(
    @PrimaryKey(autoGenerate = false) var id: String = LAST_PLAYED_AUDIO,
    var audioIndex: Int,
    var audioId: String
) : Parcelable