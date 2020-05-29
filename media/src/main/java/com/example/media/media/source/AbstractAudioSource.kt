package com.example.media.media.source

import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.media.MediaMetadataCompat
import com.example.extensions.checkForTrue
import com.example.extensions.containsCaseInsensitive
import com.example.extensions.isVersionGreaterThan
import com.example.media.media.extensions.*
import timber.log.Timber

abstract class AbstractAudioSource : AudioSource {

    @State
    var state: Int = STATE_CREATED
        set(value) {
            if (value == STATE_INITIALIZED || value == STATE_ERROR) {
                synchronized(onReadyListeners) {
                    field = value
                    onReadyListeners.forEach { listener ->
                        listener(STATE_INITIALIZED == state)
                    }
                }
            } else {
                field = value
            }
        }

    private val onReadyListeners = mutableListOf<(Boolean) -> Unit>()

    override fun whenReady(performAction: (Boolean) -> Unit): Boolean =
        when (state) {
            STATE_CREATED, STATE_INITIALIZING -> {
                onReadyListeners += performAction
                false
            }
            else -> {
                performAction(state != STATE_ERROR)
                true
            }
        }

    override fun search(query: String, extras: Bundle): List<MediaMetadataCompat> {
        val focusSearchResult = when (extras[MediaStore.EXTRA_MEDIA_FOCUS]) {
            MediaStore.Audio.Genres.ENTRY_CONTENT_TYPE -> {
                val genre = extras[EXTRA_MEDIA_GENRE]
                Timber.d("Focused genre search: '$genre'")
                filter { audio ->
                    audio.genre == genre
                }
            }
            MediaStore.Audio.Artists.ENTRY_CONTENT_TYPE -> {
                val artist = extras[MediaStore.EXTRA_MEDIA_ARTIST]
                Timber.d("Focused artist search: '$artist'")
                filter { audio ->
                    (audio.artist == artist || audio.albumArtist == artist)
                }
            }
            MediaStore.Audio.Albums.ENTRY_CONTENT_TYPE -> {
                val artist = extras[MediaStore.EXTRA_MEDIA_ARTIST]
                val album = extras[MediaStore.EXTRA_MEDIA_ALBUM]
                Timber.d("Focused album search: album='$album' artist='$artist")
                filter { audio ->
                    (audio.artist == artist || audio.albumArtist == artist) && audio.album == album
                }
            }
            MediaStore.Audio.Media.ENTRY_CONTENT_TYPE -> {
                val title = extras[MediaStore.EXTRA_MEDIA_TITLE]
                val album = extras[MediaStore.EXTRA_MEDIA_ALBUM]
                val artist = extras[MediaStore.EXTRA_MEDIA_ARTIST]
                Timber.d("Focused media search: title='$title' album='$album' artist='$artist")
                filter { audio ->
                    (audio.artist == artist || audio.albumArtist == artist) && audio.album == album
                            && audio.title == title
                }
            }
            else -> emptyList()
        }
        return if (focusSearchResult.isEmpty()) {
            if (query.isNotBlank()) {
                Timber.d("Unfocused search for '$query'")
                filter { audio ->
                    audio.title.containsCaseInsensitive(query)
                            || audio.genre.containsCaseInsensitive(query)
                }
            } else {
                Timber.d("Unfocused search without keyword")
                shuffled()
            }
        } else {
            focusSearchResult
        }
    }

    /**
     * [MediaStore.EXTRA_MEDIA_GENRE] is missing on API 19. Hide this fact by using our
     * own version of it.
     */
    private val EXTRA_MEDIA_GENRE: String
        get() = isVersionGreaterThan(Build.VERSION_CODES.LOLLIPOP, equals = true).checkForTrue {
            MediaStore.EXTRA_MEDIA_GENRE
        } ?: "android.intent.extra.genre"
}