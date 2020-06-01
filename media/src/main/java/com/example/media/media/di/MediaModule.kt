package com.example.media.media.di

import android.content.ComponentName
import android.support.v4.media.session.MediaControllerCompat
import android.support.v4.media.session.MediaSessionCompat
import androidx.core.app.NotificationManagerCompat
import com.example.media.R
import com.example.media.media.connection.AudioServiceConnection
import com.example.media.media.notification.NotificationBuilder
import com.example.media.media.notification.ServiceNotificationHandler
import com.example.media.media.service.AudioService
import com.example.media.media.service.MediaControllerCallbackHandler
import com.example.media.media.service.NoisyReceiver
import com.example.media.media.service.QueueNavigator
import com.example.media.media.source.RemoteSource
import com.example.media.media.validator.PackageValidator
import com.google.android.exoplayer2.ext.mediasession.MediaSessionConnector
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val mediaModule = module {
    single {
        AudioServiceConnection(
            androidContext(),
            ComponentName(androidContext(), AudioService::class.java)
        )
    }
    factory { RemoteSource() }
    factory { MediaSessionCompat(get(), AudioService.TAG) }
    factory { NoisyReceiver(get()) }
    factory { NotificationBuilder(get()) }
    factory { NotificationManagerCompat.from(get()) }
    factory { MediaSessionConnector(get()) }
    factory { PackageValidator(androidContext(), providePackageValidatorXml()) }
    factory { MediaControllerCompat(androidContext(), get() as MediaSessionCompat) }
    factory { QueueNavigator(get()) }
    factory { MediaControllerCallbackHandler(get()) }
    factory { ServiceNotificationHandler(get(), get(), get(), get(), get(), get()) }
}

fun providePackageValidatorXml(): Int {
    return R.xml.allowed_media_browser_callers
}