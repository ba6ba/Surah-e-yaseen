package com.example.media.media.service

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaControllerCompat
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import androidx.annotation.CallSuper
import androidx.core.content.ContextCompat
import androidx.media.MediaBrowserServiceCompat
import com.example.data.audio.NotificationAudioWrapper
import com.example.extensions.get
import com.example.extensions.isFalse
import com.example.extensions.isTrue
import com.example.media.media.Constants.CONTENT_STYLE_BROWSABLE_HINT
import com.example.media.media.Constants.CONTENT_STYLE_GRID
import com.example.media.media.Constants.CONTENT_STYLE_LIST
import com.example.media.media.Constants.CONTENT_STYLE_PLAYABLE_HINT
import com.example.media.media.Constants.CONTENT_STYLE_SUPPORTED
import com.example.media.media.Constants.MEDIA_SEARCH_SUPPORTED
import com.example.media.media.Constants.USER_AGENT
import com.example.media.media.connection.NETWORK_FAILURE
import com.example.media.media.extensions.flag
import com.example.media.media.notification.NOW_PLAYING_NOTIFICATION
import com.example.media.media.notification.ServiceNotificationHandler
import com.example.media.media.source.RemoteSource
import com.example.media.media.validator.PackageValidator
import com.example.network.MainDispatcher
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.audio.AudioAttributes
import com.google.android.exoplayer2.ext.mediasession.MediaSessionConnector
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import java.lang.IllegalStateException

typealias ServiceHandlerState = com.example.media.media.notification.ServiceHandler

class AudioService : MediaBrowserServiceCompat(), MediaControllerCallback {

    companion object {
        val TAG = this::class.java.simpleName
        val NAME = "com.example.media.media.service.AudioService"
        val PLAY_AUDIO = "com.example.media.media.service.AudioService.PLAY_AUDIO"
        val AUDIO_DATA = "com.example.media.media.service.AudioService.AUDIO_DATA"
    }

    private val audioNoisyReceiver : NoisyReceiver by inject()
    private val audioSource : RemoteSource by inject()
    private val packageValidator : PackageValidator by inject()
    private val mediaSession: MediaSessionCompat by inject()
    private val mediaController: MediaControllerCompat by inject()
    private val mediaSessionConnector: MediaSessionConnector by inject()
    private val queueNavigator: QueueNavigator by inject()
    private val serviceNotificationHandler: ServiceNotificationHandler by inject()

    private val serviceJob = SupervisorJob()
    private val serviceScope = CoroutineScope(MainDispatcher + serviceJob)

    private val audioAttribute by lazy {
        AudioAttributes.Builder()
            .setContentType(C.CONTENT_TYPE_MUSIC)
            .setUsage(C.USAGE_MEDIA)
            .build()
    }

    private val exoPlayer : ExoPlayer by lazy {
        ExoPlayerFactory.newSimpleInstance(this).apply {
            setAudioAttributes(audioAttribute, true)
        }
    }

    private val sessionActivityPendingIntent by lazy {
        packageManager?.getLaunchIntentForPackage(packageName)?.let { sessionIntent ->
            PendingIntent.getActivity(this, 0, sessionIntent,PendingIntent.FLAG_CANCEL_CURRENT)
        }
    }

    override fun onCreate() {
        super.onCreate()
        handleServiceNotificationHandlerCallbacks()
        setupNoisyReceiverCallback()
        setupMediaSession()
        setupMediaController()
        setupMediaSessionConnector()
    }

    private fun handleServiceNotificationHandlerCallbacks() {
        serviceNotificationHandler.serviceHandler = { state, data ->
            when(state) {
                ServiceHandlerState.STOP_SELF -> stopSelf()
                ServiceHandlerState.START_SELF -> startForeground(NOW_PLAYING_NOTIFICATION, data as Notification)
                ServiceHandlerState.START_FOREGROUND ->
                    ContextCompat.startForegroundService(this, Intent(this, this.javaClass))
                ServiceHandlerState.STOP_FOREGROUND -> stopForeground(data as Boolean)
            }
        }
    }

    private fun checkForIntent(intent: Intent?) {
        intent ?: return
        (PLAY_AUDIO == intent.action && intent.hasExtra(AUDIO_DATA) && intent.get(AUDIO_DATA) != null).isTrue {
            setupAudioClipSource(intent.get(AUDIO_DATA) as NotificationAudioWrapper)
        }
    }

    private fun setupMediaSessionConnector() {
        mediaSessionConnector.apply {
            val dataSourceFactory = DefaultDataSourceFactory(this@AudioService,
                Util.getUserAgent(this@AudioService, USER_AGENT), null)
            setPlayer(exoPlayer)
            setPlaybackPreparer(PlaybackPreparer(audioSource, exoPlayer, dataSourceFactory))
            setQueueNavigator(queueNavigator)
        }
    }

    private fun setupMediaController() {
        mediaController.registerCallback(MediaControllerCallbackHandler(this))
    }

    private fun setupNoisyReceiverCallback() {
        audioNoisyReceiver.audioGettingNoisy = {
            mediaController.transportControls.pause()
        }
    }

    private fun setupMediaSession() {
        mediaSession.apply {
            setSessionActivity(sessionActivityPendingIntent)
            isActive = true
            this@AudioService.sessionToken = sessionToken
        }
    }

    private fun setupAudioClipSource(audio: NotificationAudioWrapper) {
        serviceScope.launch {
            audioSource.load(audio)
        }
    }

    @CallSuper
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        checkForIntent(intent)
        return Service.START_NOT_STICKY
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
        exoPlayer.stop(true)
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaSession.apply {
            isActive = false
            release()
        }
        serviceJob.cancel()
    }

    override fun onLoadChildren(parentId: String, result: Result<MutableList<MediaBrowserCompat.MediaItem>>) {
        val resultSent = audioSource.whenReady { hasInitialized ->
            hasInitialized.isTrue {
                try {
                    result.sendResult(
                        audioSource.map {
                            MediaBrowserCompat.MediaItem(it.description, it.flag)
                        }.toMutableList()
                    )
                } catch (e : IllegalStateException) {
                    notifyChildrenChanged(parentId)
                }
            } ?: kotlin.run {
                mediaSession.sendSessionEvent(NETWORK_FAILURE, null)
                result.sendResult(null)
            }
        }
        resultSent.isFalse {
            result.detach()
        }
    }

    override fun onGetRoot(clientPackageName: String, clientUid: Int, rootHints: Bundle?): BrowserRoot? {
        val isKnownCaller = packageValidator.isKnownCaller(clientPackageName, clientUid)
        val rootExtras = Bundle().apply {
            putBoolean(
                MEDIA_SEARCH_SUPPORTED,
                isKnownCaller
            )
            putBoolean(CONTENT_STYLE_SUPPORTED, true)
            putInt(CONTENT_STYLE_BROWSABLE_HINT, CONTENT_STYLE_GRID)
            putInt(CONTENT_STYLE_PLAYABLE_HINT, CONTENT_STYLE_LIST)
        }
        return BrowserRoot(if (isKnownCaller) MediaHelper.ROOT_ID else MediaHelper.EMPTY_ROOT_ID, rootExtras)
    }

    override fun onSearch(query: String, extras: Bundle?, result: Result<MutableList<MediaBrowserCompat.MediaItem>>) {
        val resultsSent = audioSource.whenReady { successfullyInitialized ->
            if (successfullyInitialized) {
                result.sendResult(
                    audioSource.search(query, extras ?: Bundle.EMPTY)
                        .map { mediaMetadata ->
                            MediaBrowserCompat.MediaItem(mediaMetadata.description, mediaMetadata.flag)
                        }.toMutableList()
                )
            }
        }
        resultsSent.isFalse {
            result.detach()
        }
    }

    override fun onMetadataChanged(metadata: MediaMetadataCompat?) {
        mediaController.playbackState?.let { state ->
            serviceNotificationHandler.handleMediaCallbacksAndNotification(state)
        }
    }

    override fun onPlaybackStateChanged(state: PlaybackStateCompat?) {
        state?.let {
            serviceNotificationHandler.handleMediaCallbacksAndNotification(state)
        }
    }
}