package com.example.media.media.service

import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaControllerCompat
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat

class MediaControllerCallbackImpl(private val mediaControllerCallback: MediaControllerCallback) : MediaControllerCompat.Callback() {
    override fun onMetadataChanged(metadata: MediaMetadataCompat?) {
        mediaControllerCallback.onMetadataChanged(metadata)
    }

    override fun onPlaybackStateChanged(state: PlaybackStateCompat?) {
        mediaControllerCallback.onPlaybackStateChanged(state)
    }

    override fun onRepeatModeChanged(repeatMode: Int) {
        mediaControllerCallback.onRepeatModeChanged(repeatMode)
    }

    override fun onShuffleModeChanged(shuffleMode: Int) {
        mediaControllerCallback.onShuffleModeChanged(shuffleMode)
    }

    override fun onQueueChanged(queue: MutableList<MediaSessionCompat.QueueItem>?) {
        mediaControllerCallback.onQueueChanged(queue)
    }
}