package com.example.media.media.notification

import android.app.Notification
import android.support.v4.media.session.MediaControllerCompat
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import androidx.core.app.NotificationManagerCompat
import com.example.extensions.isFalse
import com.example.extensions.isTrue
import com.example.media.media.service.NoisyReceiver

class ServiceNotificationHandler(
    private val mediaSession: MediaSessionCompat,
    private val audioNoisyReceiver: NoisyReceiver,
    private val notificationManager: NotificationManagerCompat,
    private val notificationBuilder: NotificationBuilder,
    private val mediaController: MediaControllerCompat
) {

    private var isForegroundService = false
    lateinit var serviceHandler: (ServiceHandler, Any?) -> Unit

    suspend fun handleMediaCallbacksAndNotification(state: PlaybackStateCompat?) {
        state?.let {
            updateNotification(state)
        }
    }

    private suspend fun updateNotification(state: PlaybackStateCompat) {
        val updatedState = state.state
        val notification = if (mediaController.metadata != null
            && updatedState != PlaybackStateCompat.STATE_NONE
        ) {
            buildNotification()
        } else {
            null
        }
        checkForNotification(updatedState, notification)
    }

    private suspend fun buildNotification(): Notification? =
        notificationBuilder.buildNotification(mediaSession.sessionToken)

    private fun checkForNotification(updatedState: Int, notification: Notification?) {
        when (updatedState) {
            PlaybackStateCompat.STATE_BUFFERING,
            PlaybackStateCompat.STATE_PLAYING -> {
                startPlayingAudio(notification)
            }
            else -> {
                stopPlayingAudio(notification) {
                    stopService(updatedState)
                }
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

    private fun stopPlayingAudio(notification: Notification?, stopService: () -> Unit) {
        audioNoisyReceiver.unregister()
        isForegroundService.isTrue {
            isForegroundService = false
            removeNowPlayingNotification(false)
            stopService()
            notification?.let {
                notificationManager.notify(NOW_PLAYING_NOTIFICATION, notification)
            } ?: removeNowPlayingNotification(true)
        }
    }

    private fun removeNowPlayingNotification(removeNotification: Boolean) {
        serviceHandler(ServiceHandler.STOP_FOREGROUND, removeNotification)
    }
}