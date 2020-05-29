package com.example.media.media.service

import android.net.Uri
import android.os.Bundle
import android.os.ResultReceiver
import android.support.v4.media.session.PlaybackStateCompat
import com.example.extensions.hasData
import com.example.media.media.extensions.album
import com.example.media.media.extensions.id
import com.example.media.media.extensions.toMediaSource
import com.example.media.media.extensions.trackNumber
import com.example.media.media.source.AudioSource
import com.google.android.exoplayer2.ControlDispatcher
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ext.mediasession.MediaSessionConnector
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.upstream.DataSource
import timber.log.Timber

class PlaybackPreparer(
    private val audioSource: AudioSource,
    private val exoPlayer: ExoPlayer,
    private val dataSourceFactory: DataSource.Factory
) : MediaSessionConnector.PlaybackPreparer {

    override fun onPrepareFromSearch(query: String?, extras: Bundle?) {
        audioSource.apply {
            whenReady {
                search(query ?: "", extras ?: Bundle.EMPTY).hasData {
                    exoPlayer.prepare(it.toMediaSource(dataSourceFactory))
                }
            }
        }
    }

    override fun onCommand(player: Player?, controlDispatcher: ControlDispatcher?,
        command: String?, extras: Bundle?, cb: ResultReceiver?): Boolean = false

    override fun getSupportedPrepareActions(): Long =
        PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID or
                PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID or
                PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH or
                PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH

    override fun onPrepare() = Unit

    override fun onPrepareFromMediaId(mediaId: String?, extras: Bundle?) {
        audioSource.apply {
            whenReady {
                var initialWindowIndex = 0
                val mediaSource = find { item -> item.id == mediaId }?.let { item ->
                    initialWindowIndex = indexOf(item)
                    audioSource.filter { it.album == item.album }
                        .sortedBy { it.trackNumber }
                        .toMediaSource(dataSourceFactory)
                } ?: Timber.w("Content not found : mediaId=$mediaId")

                if (mediaSource is MediaSource) {
                    exoPlayer.prepare(mediaSource)
                    exoPlayer.seekTo(initialWindowIndex, 0)
                }
            }
        }
    }

    override fun onPrepareFromUri(uri: Uri?, extras: Bundle?) = Unit
}