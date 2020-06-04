package com.example.media.media.notification

import android.app.Notification
import android.content.Intent
import android.support.v4.media.session.MediaControllerCompat
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.example.extensions.isFalse
import com.example.extensions.isTrue
import com.example.media.media.service.AudioService
import com.example.media.media.service.NoisyReceiver
import com.example.network.MainDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class ServiceNotificationHandler(
    private val mediaSession: MediaSessionCompat,
    private val audioNoisyReceiver: NoisyReceiver,
    private val notificationManager: NotificationManagerCompat,
    private val notificationBuilder: NotificationBuilder,
    private val mediaController: MediaControllerCompat
) {

    private val serviceJob = SupervisorJob()
    private val serviceScope = CoroutineScope(MainDispatcher + serviceJob)
    private var isForegroundService = false
    lateinit var serviceHandler : (ServiceHandler, Any?) -> Unit

    fun handleMediaCallbacksAndNotification(state: PlaybackStateCompat?) {
        state?.let {
            serviceScope.launch {
                updateNotification(state)
            }
        }
    }

    private suspend fun updateNotification(state: PlaybackStateCompat) {
        val updatedState = state.state
        val notification = if (mediaController.metadata != null
            && updatedState != PlaybackStateCompat.STATE_NONE
        ) {
            notificationBuilder.buildNotification(mediaSession.sessionToken)
        } else {
            null
        }
        checkForNotification(updatedState, notification)
    }

    private fun checkForNotification(updatedState: Int, notification: Notification?) {
        when (updatedState) {
            PlaybackStateCompat.STATE_BUFFERING,
            PlaybackStateCompat.STATE_PLAYING -> {
                startPlayingAudio(notification)
            }
            else -> {
                stopPlayingAudio(notification)
                stopService(updatedState)
            }
        }
    }

    private fun stopService(updatedState: Int) {
        (PlaybackStateCompat.STATE_NONE == updatedState).isTrue {
            serviceHandler(ServiceHandler.STOP_SELF, null)
        }
    }

    private fun startPlayingAudio(notification: Notification?) {
        audioNoisyReceiver.register()
        if (notification != null) {
            notificationManager.notify(NOW_PLAYING_NOTIFICATION, notification)
            isForegroundService.isFalse {
                serviceHandler(ServiceHandler.START_FOREGROUND, null)
                serviceHandler(ServiceHandler.START_SELF, notification)
                isForegroundService = true
            }
        }
    }

    private fun stopPlayingAudio(notification: Notification?) {
        audioNoisyReceiver.unregister()
        isForegroundService.isTrue {
            isForegroundService = false
            removeNowPlayingNotification(false)
            notification?.let {
                notificationManager.notify(NOW_PLAYING_NOTIFICATION, notification)
            } ?: removeNowPlayingNotification(true)
        }
    }

    private fun removeNowPlayingNotification(removeNotification: Boolean) {
        serviceHandler(ServiceHandler.STOP_FOREGROUND, removeNotification)
    }
}