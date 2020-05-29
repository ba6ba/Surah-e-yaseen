package com.example.media.media.service

import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat

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
}