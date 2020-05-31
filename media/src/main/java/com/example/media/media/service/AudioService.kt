package com.example.media.media.service

import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaControllerCompat
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import androidx.media.MediaBrowserServiceCompat
import androidx.media.session.MediaButtonReceiver
import com.example.extensions.isFalse
import com.example.extensions.isTrue
import com.example.media.media.Constants.CONTENT_STYLE_BROWSABLE_HINT
import com.example.media.media.Constants.CONTENT_STYLE_GRID
import com.example.media.media.Constants.CONTENT_STYLE_LIST
import com.example.media.media.Constants.CONTENT_STYLE_PLAYABLE_HINT
import com.example.media.media.Constants.CONTENT_STYLE_SUPPORTED
import com.example.media.media.Constants.MEDIA_SEARCH_SUPPORTED
import com.example.media.media.Constants.USER_AGENT
import com.example.media.media.NETWORK_FAILURE
import com.example.media.media.extensions.flag
import com.example.media.media.notification.ServiceNotificationHandler
import com.example.media.media.source.AudioSource
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

class AudioService : MediaBrowserServiceCompat(), MediaControllerCallback {

    companion object {
        val TAG = this::class.java.simpleName
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
            serviceNotificationHandler.handleMediaCallbacksAndNotification(state)
        }
    }

    override fun onPlaybackStateChanged(state: PlaybackStateCompat?) {
        state?.let {
            serviceNotificationHandler.handleMediaCallbacksAndNotification(state)
        }
    }
}