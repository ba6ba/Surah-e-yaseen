package com.example.media.media.source

import android.support.v4.media.MediaMetadataCompat
import com.example.data.audio.ServiceMetaData
import com.example.media.media.extensions.from
import com.example.network.DefaultDispatcher
import kotlinx.coroutines.withContext

class RemoteSource : AbstractAudioSource() {

    private var audioList: List<MediaMetadataCompat> = emptyList()

    init {
        state = STATE_INITIALIZING
    }

    override suspend fun load(metaDataList: List<ServiceMetaData>) {
        transformAudioMetadataToMediaMetadata(metaDataList)?.let { newList ->
            audioList = newList
            state = STATE_INITIALIZED
        } ?: kotlin.run {
            audioList = emptyList()
            state = STATE_ERROR
        }
    }

    override fun iterator(): Iterator<MediaMetadataCompat> = audioList.iterator()

    private suspend fun transformAudioMetadataToMediaMetadata(metaDataList: List<ServiceMetaData>): List<MediaMetadataCompat>? {
        return withContext(DefaultDispatcher) {
            arrayListOf<MediaMetadataCompat>().apply {
                metaDataList.forEach { audioMediaData ->
                    add(MediaMetadataCompat.Builder().from(audioMediaData).build())
                }
            }
        }
    }
}