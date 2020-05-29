package com.example.media.media.service

import android.app.Notification
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaControllerCompat
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.media.MediaBrowserServiceCompat
import androidx.media.session.MediaButtonReceiver
import com.example.extensions.isFalse
import com.example.extensions.isTrue
import com.example.media.media.NETWORK_FAILURE
import com.example.media.media.extensions.flag
import com.example.media.media.notification.NOW_PLAYING_NOTIFICATION
import com.example.media.media.notification.NotificationBuilder
import com.example.media.media.source.AudioSource
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

/** Content styling constants */
private const val CONTENT_STYLE_BROWSABLE_HINT = "android.media.browse.CONTENT_STYLE_BROWSABLE_HINT"
private const val CONTENT_STYLE_PLAYABLE_HINT = "android.media.browse.CONTENT_STYLE_PLAYABLE_HINT"
private const val CONTENT_STYLE_SUPPORTED = "android.media.browse.CONTENT_STYLE_SUPPORTED"
private const val CONTENT_STYLE_LIST = 1
private const val CONTENT_STYLE_GRID = 2

const val MEDIA_SEARCH_SUPPORTED = "android.media.browse.SEARCH_SUPPORTED"
private const val USER_AGENT = "surah-e-yaseen"

class AudioService : MediaBrowserServiceCompat(), MediaControllerCallback {

    companion object {
        val TAG = this::class.java.simpleName
    }

    private lateinit var audioNoisyReceiver : NoisyReceiver
    private lateinit var notificationManager : NotificationManagerCompat
    private lateinit var notificationBuilder : NotificationBuilder
    private lateinit var audioSource : AudioSource
    private lateinit var packageValidator : PackageValidator
    private lateinit var mediaSession: MediaSessionCompat
    private lateinit var mediaController: MediaControllerCompat
    private lateinit var mediaSessionConnector: MediaSessionConnector
    private lateinit var queueNavigator: QueueNavigator

    private val serviceJob = SupervisorJob()
    private val serviceScope = CoroutineScope(MainDispatcher + serviceJob)
    private var isForegroundService = false

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
        setupNoisyReceiverCallback()
        setupMediaSession()
        setupMediaController()
        setupMediaSessionConnector()
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

    fun getAudioClipFromRemote(audioId : Int = 3709) {
        serviceScope.launch {
            audioSource.load(audioId)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        MediaButtonReceiver.handleIntent(mediaSession, intent)
        return super.onStartCommand(intent, flags, startId)
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
                result.sendResult(
                    audioSource.map {
                        MediaBrowserCompat.MediaItem(it.description, it.flag)
                    }.toMutableList()
                )
            } ?: kotlin.run {
                mediaSession.sendSessionEvent(NETWORK_FAILURE, null)
                result.sendResult(null)
            }
        }
        resultSent.isFalse {
            result.detach()
        }
    }

    override fun onLoadItem(itemId: String?, result: Result<MediaBrowserCompat.MediaItem>) {
        val resultSent = audioSource.whenReady { hasInitialized ->
            hasInitialized.isTrue {
                result.sendResult(
                    audioSource.map {
                        MediaBrowserCompat.MediaItem(it.description, it.flag)
                    }.first()
                )
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
            serviceScope.launch {
                updateNotification(state)
            }
        }
    }

    override fun onPlaybackStateChanged(state: PlaybackStateCompat?) {
        state?.let {
            serviceScope.launch {
                updateNotification(state)
            }
        }
    }

    private suspend fun updateNotification(state: PlaybackStateCompat) {
        val updatedState = state.state
        val notification = if (mediaController.metadata != null
            && updatedState != PlaybackStateCompat.STATE_NONE) {
            notificationBuilder.buildNotification(mediaSession.sessionToken)
        } else {
            null
        }
        checkForNotification(updatedState, notification)
    }

    private fun checkForNotification(updatedState: Int, notification: Notification?) {
        when(updatedState) {
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
            stopSelf()
        }
    }

    private fun startPlayingAudio(notification: Notification?) {
        audioNoisyReceiver.register()
        if (notification != null) {
            notificationManager.notify(NOW_PLAYING_NOTIFICATION, notification)
            isForegroundService.isFalse {
                ContextCompat.startForegroundService(
                    applicationContext,
                    Intent(applicationContext, this@AudioService.javaClass)
                )
                startForeground(NOW_PLAYING_NOTIFICATION, notification)
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
        stopForeground(removeNotification)
    }
}