package com.example.media.media.source

import android.support.v4.media.MediaMetadataCompat
import com.example.data.Audio
import com.example.media.media.extensions.from
import com.example.network.DefaultDispatcher
import com.example.network.IODispatcher
import com.example.network.repository.TilawatRepository
import com.example.shared.SURAH_E_YASEEN
import com.example.shared.getSurahYaseen
import kotlinx.coroutines.withContext
import java.util.*

class RemoteSource : AbstractAudioSource() {

    private var catalog: List<MediaMetadataCompat> = emptyList()

    init {
        state = STATE_INITIALIZING
    }

    override suspend fun load(audio : Audio) {
        fetchAudio(audio)?.let { newCatalog ->
            catalog = newCatalog
            state = STATE_INITIALIZED
        } ?: kotlin.run {
            catalog = emptyList()
            state = STATE_ERROR
        }
    }

    override fun iterator(): Iterator<MediaMetadataCompat> = catalog.iterator()

    private suspend fun fetchAudio(audio: Audio): List<MediaMetadataCompat>? {
        return withContext(DefaultDispatcher) {
            arrayListOf<MediaMetadataCompat>().apply {
                add(MediaMetadataCompat.Builder()
                    .from(AudioClip.from(audio, "Rashid Alafasfy",
                        SURAH_E_YASEEN.toLowerCase(Locale.ROOT), 1, ""))
                    .build()
                )
            }
        }
    }
}