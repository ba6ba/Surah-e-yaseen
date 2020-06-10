package com.example.data.audio

import android.graphics.Bitmap
import android.net.Uri
import java.io.Serializable

private const val GENRE: String = ""
private const val INVALID_PROGRESS = -1L
private const val INVALID_ID = -1
private const val EMPTY_STRING = ""

data class AudioMediaData(
    var title: String = EMPTY_STRING, var subTitle: String = EMPTY_STRING,
    var album: String = title, var genre: String = GENRE, var data: Data? = null,
    var imageMetaData: ImageMetaData? = ImageMetaData(), var metaData: MetaData? = MetaData(), var authorData: AuthorData? = AuthorData(),
    var mediaMetaData: MediaMetaData? = MediaMetaData()
) : Serializable {

    private constructor(builder: Builder) : this(
        builder.title, builder.subTitle,
        data = builder.data, imageMetaData = builder.imageMetaData,
        authorData = builder.authorData, mediaMetaData = builder.mediaMetaData ?: MediaMetaData(),
        metaData = builder.metaData
    )

    data class Data(
        var id: Int = INVALID_ID,
        var number: Long = INVALID_PROGRESS, var totalNumber: Long = INVALID_PROGRESS
    )

    data class MediaMetaData(
        var mediaId: String = EMPTY_STRING,
        var mediaUri: Uri = Uri.EMPTY,
        var isBrowsable: Boolean = false,
        var isPlayable: Boolean = false
    ) : Serializable

    data class ImageMetaData(
        var imageUri: String = EMPTY_STRING,
        var imageDrawableRes: Int = INVALID_ID, var bitmap: Bitmap? = null
    ) : Serializable

    data class MetaData(
        var url: String = EMPTY_STRING,
        var audioDuration: Long = INVALID_PROGRESS, var audioProgress: Long = INVALID_PROGRESS,
        var displayableDuration: String = EMPTY_STRING, var displayableProgress: String = EMPTY_STRING,
        var playbackState: PlaybackState = PlaybackState.PAUSE, var format: String = EMPTY_STRING
    ) : Serializable

    data class AuthorData constructor(var id: Int = INVALID_ID, var name: String = EMPTY_STRING, var detail: Any? = null) : Serializable

    data class ServiceMetaData constructor(
        var id: Int = INVALID_ID, var authorName: String,
        var url: String = EMPTY_STRING, var byteArray: ByteArray = byteArrayOf(),
        var title: String = EMPTY_STRING, var audioDuration: Long = INVALID_PROGRESS
    ) : Serializable {

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as ServiceMetaData

            if (id != other.id) return false
            if (authorName != other.authorName) return false
            if (url != other.url) return false
            if (!byteArray.contentEquals(other.byteArray)) return false
            if (title != other.title) return false
            if (audioDuration != other.audioDuration) return false

            return true
        }

        override fun hashCode(): Int {
            var result = id
            result = 31 * result + authorName.hashCode()
            result = 31 * result + url.hashCode()
            result = 31 * result + (byteArray?.contentHashCode() ?: 0)
            result = 31 * result + title.hashCode()
            result = 31 * result + audioDuration.hashCode()
            return result
        }
    }

    enum class PlaybackState(var imageRes: Int = 0) {
        PLAYING,
        PAUSE,
        PLAY_NEXT,
        PLAY_PREVIOUS,
        STOP,
        NONE;

        companion object {
            fun get(playing: Boolean, playState : PlaybackState, pauseState : PlaybackState) = if (playing) playState else pauseState
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