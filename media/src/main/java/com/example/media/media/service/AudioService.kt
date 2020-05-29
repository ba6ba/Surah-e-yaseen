package com.example.media.media.service

import android.app.Notification
import android.app.PendingIntent
import android.content.Intent
import android.media.browse.MediaBrowser
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
import com.example.media.R
import com.example.media.media.NETWORK_FAILURE
import com.example.media.media.extensions.flag
import com.example.media.media.notification.NOW_PLAYING_NOTIFICATION
import com.example.media.media.notification.NotificationBuilder
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
import org.koin.android.ext.android.get

/** Content styling constants */
private const val CONTENT_STYLE_BROWSABLE_HINT = "android.media.browse.CONTENT_STYLE_BROWSABLE_HINT"
private const val CONTENT_STYLE_PLAYABLE_HINT = "android.media.browse.CONTENT_STYLE_PLAYABLE_HINT"
private const val CONTENT_STYLE_SUPPORTED = "android.media.browse.CONTENT_STYLE_SUPPORTED"
private const val CONTENT_STYLE_LIST = 1
private const val CONTENT_STYLE_GRID = 2

const val MEDIA_SEARCH_SUPPORTED = "android.media.browse.SEARCH_SUPPORTED"

private const val USER_AGENT = "surah-e-yaseen"

class AudioService : MediaBrowserServiceCompat(), MediaControllerCallback {

    private lateinit var audioNoisyReceiver : NoisyReceiver
    private lateinit var notificationManager : NotificationManagerCompat
    private lateinit var notificationBuilder : NotificationBuilder
    private lateinit var audioSource : AudioSource
    private lateinit var packageValidator : PackageValidator

    private val serviceJob = SupervisorJob()
    private val serviceScope = CoroutineScope(MainDispatcher + serviceJob)

    private lateinit var mediaSession: MediaSessionCompat
    private lateinit var mediaController: MediaControllerCompat
    private lateinit var mediaSessionConnector: MediaSessionConnector

    private var isForegroundService = false
    private val audioAttribute = AudioAttributes.Builder()
        .setContentType(C.CONTENT_TYPE_MUSIC)
        .setUsage(C.USAGE_MEDIA)
        .build()

    private val exoPlayer : ExoPlayer by lazy {
        ExoPlayerFactory.newSimpleInstance(this).apply {
            setAudioAttributes(audioAttribute, true)
        }
    }

    override fun onCreate() {
        super.onCreate()
        val sessionActivityPendingIntent = packageManager?.getLaunchIntentForPackage(packageName)?.let { sessionIntent ->
            PendingIntent.getActivity(this, 0, sessionIntent,PendingIntent.FLAG_CANCEL_CURRENT)
        }

        mediaSession = MediaSessionCompat(this, this::class.java.simpleName)
            .apply {
                setSessionActivity(sessionActivityPendingIntent)
                isActive = true
            }

        sessionToken = mediaSession.sessionToken
        mediaController = MediaControllerCompat(this, mediaSession)
            .also {
                it.registerCallback(MediaControllerCallbackImpl(this))
            }
        notificationBuilder = NotificationBuilder(this)
        notificationManager = NotificationManagerCompat.from(this)
        audioNoisyReceiver = NoisyReceiver(this, mediaSession.sessionToken)
        audioSource = RemoteSource(get())
        createAudioCatalog()
        mediaSessionConnector = MediaSessionConnector(mediaSession)
            .also { mediaSessionConnector ->
                val dataSourceFactory = DefaultDataSourceFactory(
                    this, Util.getUserAgent(this, USER_AGENT), null
                )
                mediaSessionConnector.apply {
                    setPlayer(exoPlayer)
                    setPlaybackPreparer(PlaybackPreparer(audioSource, exoPlayer, dataSourceFactory))
                    setQueueNavigator(QueueNavigator(mediaSession))
                }
            }
        packageValidator = PackageValidator(this, R.xml.allowed_media_browser_callers)
    }

    fun createAudioCatalog(audioId : Int = 3709) {
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

    override fun onRepeatModeChanged(repeatMode: Int) {
        //
    }

    override fun onShuffleModeChanged(shuffleMode: Int) {
        //
    }

    override fun onQueueChanged(queue: MutableList<MediaSessionCompat.QueueItem>?) {
        //
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
            else -> {
                audioNoisyReceiver.unregister()
                isForegroundService.isTrue {
                    isForegroundService = false
                    removeNowPlayingNotification(false)
                    (PlaybackStateCompat.STATE_NONE == updatedState).isTrue {
                        stopSelf()
                    }
                    notification?.let {
                        notificationManager.notify(NOW_PLAYING_NOTIFICATION, notification)
                    } ?: removeNowPlayingNotification(true)
                }
            }
        }
    }

    private fun removeNowPlayingNotification(removeNotification: Boolean) {
        stopForeground(removeNotification)
    }
}