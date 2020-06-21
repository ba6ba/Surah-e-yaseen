package com.example.daos

import android.graphics.Bitmap
import android.net.Uri
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

const val GENRE: String = ""
const val INVALID_PROGRESS = -1L
const val INVALID_ID = -1
const val EMPTY_STRING = ""

typealias State = AudioMediaData.PlaybackState

@Entity
@Parcelize
data class AudioMediaData(
    @PrimaryKey(autoGenerate = false) var id: String = EMPTY_STRING,
    var title: String = EMPTY_STRING, var subTitle: String = EMPTY_STRING,
    var album: String = title, var genre: String = GENRE, var data: Data? = null,
    var imageMetaData: ImageMetaData? = ImageMetaData(), var metaData: MetaData? = MetaData(), var authorData: AuthorData? = AuthorData(),
    var mediaMetaData: MediaMetaData? = MediaMetaData()
) : Parcelable {

    private constructor(builder: Builder) : this(
        title = builder.title, subTitle = builder.subTitle,
        data = builder.data, imageMetaData = builder.imageMetaData,
        authorData = builder.authorData, mediaMetaData = builder.mediaMetaData ?: MediaMetaData(),
        metaData = builder.metaData
    )

    @Parcelize
    data class Data(
        var id: Int = INVALID_ID, var audioId: String = EMPTY_STRING,
        var totalNumber: Int = INVALID_PROGRESS.toInt()
    ) : Parcelable

    @Parcelize
    data class MediaMetaData(
        var mediaId: String = EMPTY_STRING,
        var mediaUri: Uri = Uri.EMPTY,
        var isBrowsable: Boolean = false,
        var isPlayable: Boolean = false
    ) : Parcelable

    @Parcelize
    data class ImageMetaData(
        var imageUri: String = EMPTY_STRING,
        var imageDrawableRes: Int = INVALID_ID, var bitmap: Bitmap? = null
    ) : Parcelable

    @Parcelize
    data class MetaData(
        var url: String = EMPTY_STRING, var number: Long = INVALID_PROGRESS,
        var audioDuration: Long = INVALID_PROGRESS, var audioProgress: Long = INVALID_PROGRESS,
        var displayableDuration: String = EMPTY_STRING, var displayableProgress: String = EMPTY_STRING,
        var playbackState: PlaybackState = PlaybackState.PAUSE, var format: String = EMPTY_STRING
    ) : Parcelable

    @Parcelize
    data class AuthorData constructor(var id: Int = INVALID_ID, var name: String = EMPTY_STRING, var detail: String? = null) : Parcelable

    enum class PlaybackState(var imageRes: Int = 0) {
        PLAYING,
        PAUSE;

        companion object {
            fun get(playing: Boolean, playState: PlaybackState, pauseState: PlaybackState) = if (playing) playState else pauseState
        }
    }

    class Builder {
        var data: Data? = null
        var authorData: AuthorData? = null
        var metaData: MetaData? = null
        var mediaMetaData: MediaMetaData? = null
        var imageMetaData: ImageMetaData? = null
        var title: String = EMPTY_STRING
        var subTitle: String = EMPTY_STRING

        fun build() = AudioMediaData(this)
    }

    companion object {
        inline fun build(crossinline block: Builder.() -> Unit) = Builder().apply(block).build()
    }
}