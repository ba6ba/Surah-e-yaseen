package com.example.media.media.connection

import android.content.ComponentName
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.ResultReceiver
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

    fun sendCommand(
        command: String,
        parameters: Bundle?,
        resultCallback: ((Int, Bundle?) -> Unit)
    ) = if (mediaBrowser.isConnected) {
        mediaController.sendCommand(command, parameters, object : ResultReceiver(Handler()) {
            override fun onReceiveResult(resultCode: Int, resultData: Bundle?) {
                resultCallback(resultCode, resultData)
            }
        })
        true
    } else {
        false
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
