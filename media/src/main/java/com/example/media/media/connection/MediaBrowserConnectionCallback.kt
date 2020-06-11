package com.example.media.media.connection

import android.support.v4.media.MediaBrowserCompat

class MediaBrowserConnectionCallback(private val callback: AudioBrowserConnectionCallback) :
    MediaBrowserCompat.ConnectionCallback() {

    /**
     * Invoked after [MediaBrowserCompat.connect] when the request has successfully
     * completed.
     */
    override fun onConnected() {
        callback.onConnectionSuccessful()
    }

    /**
     * Invoked when the client is disconnected from the media browser.
     */
    override fun onConnectionSuspended() {
        callback.onConnectionFailure()
    }

    /**
     * Invoked when the connection to the media browser failed.
     */
    override fun onConnectionFailed() {
        callback.onConnectionFailure()
    }
}