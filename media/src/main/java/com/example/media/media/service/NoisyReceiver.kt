package com.example.media.media.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.AudioManager
import android.support.v4.media.session.MediaControllerCompat
import android.support.v4.media.session.MediaSessionCompat
import com.example.extensions.isFalse
import com.example.extensions.isTrue

/**
 * Helper class for listening for when headphones are unplugged (or the audio
 * will otherwise cause playback to become "noisy").
 */
class NoisyReceiver constructor(private val context: Context, sessionToken : MediaSessionCompat.Token) : BroadcastReceiver(){

    private val mediaController : MediaControllerCompat = MediaControllerCompat(context, sessionToken)
    private var isRegistered : Boolean = false

    fun register() {
        isRegistered.isFalse {
            context.registerReceiver(this@NoisyReceiver, IntentFilter(AudioManager.ACTION_AUDIO_BECOMING_NOISY))
            isRegistered = true
        }
    }

    fun unregister() {
        isRegistered.isTrue {
            context.unregisterReceiver(this@NoisyReceiver)
            isRegistered = false
        }
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        (AudioManager.ACTION_AUDIO_BECOMING_NOISY == intent?.action).isTrue {
            mediaController.transportControls.pause()
        }
    }
}