package com.example.data.audio

import java.io.Serializable

data class ServiceMetaData constructor(
        var id: Int = INVALID_ID, var authorName: String,
        var url: String = EMPTY_STRING, var byteArray: ByteArray = byteArrayOf(),
        var title: String = EMPTY_STRING, var audioDuration: Long = INVALID_PROGRESS,
        var audioId : String = EMPTY_STRING
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