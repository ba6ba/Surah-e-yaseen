package com.example.media.media.connection

import android.content.ComponentName
import android.content.Context
import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaControllerCompat
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import androidx.lifecycle.MutableLiveData
import androidx.media.MediaBrowserServiceCompat
import com.example.media.media.extensions.id
import com.example.media.media.service.MediaControllerCallback
import com.example.media.media.service.MediaControllerCallbackHandler

/**
 * Class that manages a connection to a [MediaBrowserServiceCompat] instance, typically a
 * [MusicService] or one of its subclasses.
 *
 * Typically it's best to construct/inject dependencies either using DI or, as UAMP does,
 * using [InjectorUtils] in the app module. There are a few difficulties for that here:
 * - [MediaBrowserCompat] is a final class, so mocking it directly is difficult.
 * - A [MediaBrowserConnectionCallback] is a parameter into the construction of
 *   a [MediaBrowserCompat], and provides callbacks to this class.
 * - [MediaBrowserCompat.ConnectionCallback.onConnected] is the best place to construct
 *   a [MediaControllerCompat] that will be used to control the [MediaSessionCompat].
 *
 *  Because of these reasons, rather than constructing additional classes, this is treated as
 *  a black box (which is why there's very little logic here).
 *
 *  This is also why the parameters to construct a [MusicServiceConnection] are simple
 *  parameters, rather than private properties. They're only required to build the
 *  [MediaBrowserConnectionCallback] and [MediaBrowserCompat] objects.
 */

const val NETWORK_FAILURE = "com.example.media.session.NETWORK_FAILURE"

class AudioServiceConnection(private val context: Context, serviceComponent: ComponentName) : AudioBrowserConnectionCallback,
    MediaControllerCallback {

    private lateinit var mediaController: MediaControllerCompat
    val rootMediaId: String get() = mediaBrowser.root

    private val isConnected = MutableLiveData<Boolean>()
        .apply { postValue(false) }
    val networkFailure = MutableLiveData<Boolean>()
        .apply { postValue(false) }

    val playbackState = MutableLiveData<PlaybackStateCompat>()
        .apply { postValue(EMPTY_PLAYBACK_STATE) }
    val nowPlaying = MutableLiveData<MediaMetadataCompat>()
        .apply { postValue(NOTHING_PLAYING) }

    val transportControls: MediaControllerCompat.TransportControls
        get() = mediaController.transportControls

    private val mediaBrowserConnectionCallback by lazy {
        MediaBrowserConnectionCallback(this)
    }

    private val mediaBrowser by lazy {
        MediaBrowserCompat(
            context,
            serviceComponent,
            mediaBrowserConnectionCallback, null
        ).apply { connect() }
    }

    fun subscribe(parentId: String, callback: MediaBrowserCompat.SubscriptionCallback) {
        mediaBrowser.subscribe(parentId, callback)
    }

    fun unsubscribe(parentId: String, callback: MediaBrowserCompat.SubscriptionCallback) {
        mediaBrowser.unsubscribe(parentId, callback)
    }

    override fun onConnectionSuccessful() {
        isConnected.postValue(true)
        mediaController = MediaControllerCompat(context, mediaBrowser.sessionToken).apply {
            registerCallback(MediaControllerCallbackHandler(this@AudioServiceConnection))
        }
    }

    override fun onConnectionFailure() {
        isConnected.postValue(false)
    }

    override fun onSessionDestroyed() {
        mediaBrowserConnectionCallback.onConnectionSuspended()
    }

    override fun onSessionEvent(event: String?, extras: Bundle?) {
        when (event) {
            NETWORK_FAILURE -> networkFailure.postValue(true)
        }
    }

    override fun onMetadataChanged(metadata: MediaMetadataCompat?) {
        nowPlaying.postValue(
            if (metadata?.id == null) {
                NOTHING_PLAYING
            } else {
                metadata
            }
        )
    }

    override fun onPlaybackStateChanged(state: PlaybackStateCompat?) {
        playbackState.postValue(state ?: EMPTY_PLAYBACK_STATE)
    }
}
