package com.example.data.audio

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
    @PrimaryKey(autoGenerate = false)
    var id: String = EMPTY_STRING,
    var data: Data? = null,
    var genre: String = GENRE,
    var title: String = EMPTY_STRING,
    var album: String = EMPTY_STRING,
    var subTitle: String = EMPTY_STRING,
    var metaData: MetaData? = MetaData(),
    var authorData: AuthorData? = AuthorData(),
    var imageMetaData: ImageMetaData? = ImageMetaData(),
    var mediaMetaData: MediaMetaData? = MediaMetaData()
) : Parcelable {

    private constructor(builder: Builder) : this(
        id = builder.id,
        data = builder.data,
        title = builder.title,
        subTitle = builder.subTitle,
        metaData = builder.metaData,
        authorData = builder.authorData,
        imageMetaData = builder.imageMetaData,
        mediaMetaData = builder.mediaMetaData ?: MediaMetaData()
    )

    @Parcelize
    data class Data(
        var id: Int = INVALID_ID,
        var audioId: String = EMPTY_STRING,
        var totalNumber: Int = INVALID_PROGRESS.toInt()
    ) : Parcelable

    @Parcelize
    data class MediaMetaData(
        var mediaId: String = EMPTY_STRING,
        var mediaUri: String = EMPTY_STRING,
        var isBrowsable: Boolean = false,
        var isPlayable: Boolean = false
    ) : Parcelable

    @Parcelize
    data class ImageMetaData(
        var imageUri: String = EMPTY_STRING,
        var imageDrawableRes: Int = INVALID_ID
    ) : Parcelable

    @Parcelize
    data class MetaData(
        var url: String = EMPTY_STRING,
        var format: String = EMPTY_STRING,
        var number: Long = INVALID_PROGRESS,
        var audioDuration: Long = INVALID_PROGRESS,
        var audioProgress: Long = INVALID_PROGRESS,
        var displayableDuration: String = EMPTY_STRING,
        var displayableProgress: String = EMPTY_STRING,
        var playbackState: PlaybackState = PlaybackState.PAUSE
    ) : Parcelable

    @Parcelize
    data class AuthorData constructor(
        var id: Int = INVALID_ID,
        var name: String = EMPTY_STRING,
        var detail: String? = null
    ) : Parcelable

    enum class PlaybackState(var imageRes: Int = 0) {
        PLAYING,
        PAUSE,
        NONE;

        companion object {
            fun get(playing: Boolean, playState: PlaybackState, pauseState: PlaybackState) = if (playing) playState else pauseState
        }
    }

    class Builder {
        var data: Data? = null
        var id: String = EMPTY_STRING
        var metaData: MetaData? = null
        var title: String = EMPTY_STRING
        var authorData: AuthorData? = null
        var subTitle: String = EMPTY_STRING
        var mediaMetaData: MediaMetaData? = null
        var imageMetaData: ImageMetaData? = null

        fun build() = AudioMediaData(this)
    }

    companion object {
        inline fun build(crossinline block: Builder.() -> Unit) = Builder().apply(block).build()
    }
}