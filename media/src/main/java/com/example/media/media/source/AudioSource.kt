package com.example.media.media.source

import android.os.Bundle
import android.support.v4.media.MediaMetadataCompat
import com.example.data.audio.AudioMediaData

interface AudioSource : Iterable<MediaMetadataCompat> {

    /**
     * Begins loading the data for this music source.
     */

    suspend fun load(metaDataList: List<AudioMediaData.ServiceMetaData>)

    fun whenReady(performAction: (Boolean) -> Unit): Boolean

    fun search(query: String, extras: Bundle): List<MediaMetadataCompat>

}