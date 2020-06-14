package com.example.media.media.notification

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.support.v4.media.MediaDescriptionCompat
import android.support.v4.media.session.MediaControllerCompat
import android.support.v4.media.session.MediaSessionCompat
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.extensions.isTrue
import com.example.extensions.isVersionGreaterThan
import com.example.media.R
import com.example.media.media.extensions.isPlayEnabled
import com.example.media.media.extensions.isPlaying
import com.example.media.media.extensions.isSkipToNextEnabled
import com.example.media.media.extensions.isSkipToPreviousEnabled
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

const val NOW_PLAYING_CHANNEL: String = "com.example.media.service.NOW_PLAYING"
const val NOW_PLAYING_NOTIFICATION: Int = 0xb339

class NotificationBuilder(private val context: Context,
    private val notificationActions: NotificationActions = NotificationActions(context)) {

    private val platformNotificationManager: NotificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    @SuppressLint("NewApi")
    suspend fun buildNotification(sessionToken: MediaSessionCompat.Token): Notification {
        shouldCreateNowPlayingChannel.isTrue {
            createNowPlayingChannel()
        }

        val controller = MediaControllerCompat(context, sessionToken)
        val description = controller.metadata.description
        val playbackState = controller.playbackState

        val builder = NotificationCompat.Builder(context, NOW_PLAYING_CHANNEL)
        var playPauseIndex = 0
        playbackState.apply {
            isSkipToPreviousEnabled.isTrue {
                builder.addAction(notificationActions.skipToPreviousAction)
                ++playPauseIndex
            }
            isPlaying.isTrue {
                builder.addAction(notificationActions.pauseAction)
            } ?: isPlayEnabled.isTrue {
                builder.addAction(notificationActions.playAction)
            }
            isSkipToNextEnabled.isTrue {
                builder.addAction(notificationActions.skipToNextAction)
            }
        }

        return builder.setContentIntent(controller.sessionActivity)
            .setContentText(description.subtitle)
            .setContentTitle(description.title)
            .setDeleteIntent(notificationActions.stopPendingIntent)
            .setLargeIcon(description.iconBitmap)
            .setOnlyAlertOnce(true)
            .setSmallIcon(R.drawable.exo_icon_play)
            .setStyle(mediaStyle(sessionToken, playPauseIndex))
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .build()
    }

    private fun mediaStyle(sessionToken: MediaSessionCompat.Token, actionsInCompactView: Int): NotificationCompat.Style? = run {
        androidx.media.app.NotificationCompat.MediaStyle()
            .setCancelButtonIntent(notificationActions.stopPendingIntent)
            .setMediaSession(sessionToken)
            .setShowActionsInCompactView(actionsInCompactView)
            .setShowCancelButton(true)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNowPlayingChannel() {
        val notificationChannel = NotificationChannel(
            NOW_PLAYING_CHANNEL,
            context.getString(R.string.notification_channel),
            NotificationManager.IMPORTANCE_LOW
        )
            .apply {
                description = context.getString(R.string.notification_channel_description)
            }

        platformNotificationManager.createNotificationChannel(notificationChannel)
    }

    private val shouldCreateNowPlayingChannel
        get() = isVersionGreaterThan(Build.VERSION_CODES.O, equals = true) && isNowPlayingChannelExists.not()

    private val isNowPlayingChannelExists
        @RequiresApi(Build.VERSION_CODES.O)
        get() = platformNotificationManager.getNotificationChannel(NOW_PLAYING_CHANNEL) != null
}