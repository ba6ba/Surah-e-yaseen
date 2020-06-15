package com.example.media.media.service

import android.os.Bundle
import android.os.ResultReceiver
import com.example.media.media.service.AudioService.Companion.REFRESH_AUDIO_DATA
import com.google.android.exoplayer2.ControlDispatcher
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ext.mediasession.MediaSessionConnector

typealias CommandHandler = (parameters: Bundle, callback: ResultReceiver?) -> Boolean

class AudioServiceCustomCommandReceiver : MediaSessionConnector.CommandReceiver {

    lateinit var onRefreshAudioListCommandCallback : CommandHandler

    override fun onCommand(
        player: Player?,
        controlDispatcher: ControlDispatcher?,
        command: String?,
        extras: Bundle?,
        callback: ResultReceiver?
    ): Boolean =
        when (command) {
            REFRESH_AUDIO_DATA -> onRefreshAudioListCommandCallback(extras ?: Bundle.EMPTY, callback)
            else -> false
        }
}