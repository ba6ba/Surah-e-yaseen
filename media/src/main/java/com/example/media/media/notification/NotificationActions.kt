package com.example.media.media.notification

import android.content.Context
import android.support.v4.media.session.PlaybackStateCompat
import androidx.core.app.NotificationCompat
import androidx.media.session.MediaButtonReceiver
import com.example.media.R

class NotificationActions(val context: Context) {

    val skipToPreviousAction = NotificationCompat.Action(
        R.drawable.exo_controls_previous,
        context.getString(R.string.notification_skip_to_previous),
        MediaButtonReceiver.buildMediaButtonPendingIntent(context, PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS)
    )

    val playAction = NotificationCompat.Action(
        R.drawable.exo_controls_play,
        context.getString(R.string.notification_play),
        MediaButtonReceiver.buildMediaButtonPendingIntent(context, PlaybackStateCompat.ACTION_PLAY)
    )

    val pauseAction = NotificationCompat.Action(
        R.drawable.exo_controls_pause,
        context.getString(R.string.notification_pause),
        MediaButtonReceiver.buildMediaButtonPendingIntent(context, PlaybackStateCompat.ACTION_PAUSE)
    )

    val skipToNextAction = NotificationCompat.Action(
        R.drawable.exo_controls_next,
        context.getString(R.string.notification_skip_to_next),
        MediaButtonReceiver.buildMediaButtonPendingIntent(context, PlaybackStateCompat.ACTION_SKIP_TO_NEXT)
    )

    val stopPendingIntent =
        MediaButtonReceiver.buildMediaButtonPendingIntent(context, PlaybackStateCompat.ACTION_STOP)

}