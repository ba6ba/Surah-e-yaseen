package com.example.media.media.source

import android.support.v4.media.MediaMetadataCompat
import com.example.media.media.extensions.from
import com.example.network.IODispatcher
import com.example.network.repository.TilawatRepository
import com.example.shared.SURAH_E_YASEEN
import com.example.shared.getSurahYaseen
import kotlinx.coroutines.withContext

class RemoteSource(private val tilawatRepository: TilawatRepository) : AbstractAudioSource() {

    private var catalog: List<MediaMetadataCompat> = emptyList()

    init {
        state = STATE_INITIALIZING
    }

    override suspend fun load(audioId : Int) {
        fetchAudio(audioId)?.let { newCatalog ->
            catalog = newCatalog
            state = STATE_INITIALIZED
        } ?: kotlin.run {
            catalog = emptyList()
            state = STATE_ERROR
        }
    }

    override fun iterator(): Iterator<MediaMetadataCompat> = catalog.iterator()

    private suspend fun fetchAudio(audioId: Int): List<MediaMetadataCompat>? {
        return withContext(IODispatcher) {
            val catalog = try {
                if (audioId > 0) fetchData(audioId) else null
            } catch (e: Exception) {
                return@withContext null
            }
            arrayListOf<MediaMetadataCompat>().apply {
                catalog?.audio?.let {
                    add(MediaMetadataCompat.Builder()
                        .from(AudioClip.from(it, "Rashid Alafasfy",
                            SURAH_E_YASEEN.capitalize(), 1, ""))
                        .build()
                    )
                    add(MediaMetadataCompat.Builder()
                        .from(AudioClip.from(it.apply {
                            url = "https://verses.quran.com/Alafasy/mp3/036083.mp3"
                        }, "Rashid Alafasfy",
                            SURAH_E_YASEEN.capitalize(), 2, ""))
                        .build()
                    )
                }
            }
        }
    }

    private suspend fun fetchData(audioId: Int) =
        tilawatRepository.getTilawatAudio(getSurahYaseen(), audioId, 7, null)
}