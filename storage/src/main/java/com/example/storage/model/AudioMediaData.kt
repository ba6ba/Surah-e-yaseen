package com.example.storage.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

internal const val GENRE: String = ""
internal const val INVALID_PROGRESS = -1L
internal const val INVALID_ID = -1
internal const val EMPTY_STRING = ""

@Parcelize
@Entity
data class AudioMediaData(
    @PrimaryKey var id : String = EMPTY_STRING,
    var title: String = EMPTY_STRING,
    var subTitle: String = EMPTY_STRING,
    var album: String = title,
    var genre: String = GENRE,
    var data: Data? = null,
    var imageMetaData: ImageMetaData? = ImageMetaData(),
    var metaData: MetaData? = MetaData(),
    var authorData: AuthorData? = AuthorData()
) : Parcelable