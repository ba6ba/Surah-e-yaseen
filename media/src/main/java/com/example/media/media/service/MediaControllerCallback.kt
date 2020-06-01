package com.example.media.media.service

import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaControllerCompat
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import androidx.media.MediaBrowserServiceCompat
import com.example.media.media.connection.NETWORK_FAILURE

interface MediaControllerCallback {

    fun onMetadataChanged(metadata: MediaMetadataCompat?)

    fun onPlaybackStateChanged(state: PlaybackStateCompat?)

    fun onRepeatModeChanged(repeatMode: Int) {
        //
    }

    fun onShuffleModeChanged(shuffleMode: Int) {
        //
    }

    fun onQueueChanged(queue: MutableList<MediaSessionCompat.QueueItem>?) {
        //
    }

    fun onSessionEvent(event: String?, extras: Bundle?) {
        //
    }

    fun onSessionDestroyed() {

    }
}