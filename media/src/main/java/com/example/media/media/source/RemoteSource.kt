package com.example.media.media.source

import android.content.Context
import android.graphics.BitmapFactory
import android.support.v4.media.MediaMetadataCompat
import com.example.data.audio.NotificationAudioWrapper
import com.example.media.media.extensions.from
import com.example.network.DefaultDispatcher
import kotlinx.coroutines.withContext

class RemoteSource(private val context: Context) : AbstractAudioSource() {

    private var catalog: List<MediaMetadataCompat> = emptyList()

    init {
        state = STATE_INITIALIZING
    }

    override suspend fun load(audio: NotificationAudioWrapper) {
        fetchAudio(audio)?.let { newCatalog ->
            catalog = newCatalog
            state = STATE_INITIALIZED
        } ?: kotlin.run {
            catalog = emptyList()
            state = STATE_ERROR
        }
    }

    override fun iterator(): Iterator<MediaMetadataCompat> = catalog.iterator()

    private suspend fun fetchAudio(wrapper: NotificationAudioWrapper): List<MediaMetadataCompat>? {
        return withContext(DefaultDispatcher) {
            arrayListOf<MediaMetadataCompat>().apply {
                add(
                    MediaMetadataCompat.Builder().from(
                        AudioClip.from(
                            wrapper.audio, wrapper.author,
                            wrapper.mainTitle, wrapper.number,
                            "", BitmapFactory.decodeResource(context.resources, wrapper.drawableId)
                        )
                    ).build()
                )
            }
        }
    }
}