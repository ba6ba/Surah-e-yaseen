package com.example.tilawat.dataprovider

import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.PlaybackStateCompat
import com.example.data.audio.Audio
import com.example.data.audio.AudioMediaData

interface IAudioData {
    val data : AudioMediaData.Data
    val metadata : AudioMediaData.MetaData
    val authorData : AudioMediaData.AuthorData
    val mediaMetadata : AudioMediaData.MediaMetaData
    val imageMetadata : AudioMediaData.ImageMetaData
    fun getAll() : List<AudioMediaData>
    fun getCurrentPlayingMediaMetadata() : AudioMediaData.MediaMetaData
    fun get(index : Int) : AudioMediaData
    fun loadAudioData(audio: Audio?, callBack : List<AudioMediaData.ServiceMetaData>.() -> Unit)
    fun fetchFromRemoteOrPlayFromLocal(verseNumber: Int, fetchFromRemote : (Boolean) -> Unit)
    fun transformMediaItemDataToAudioMediaData(children: MutableList<MediaBrowserCompat.MediaItem>, itemToPlay: AudioMediaData.() -> Unit)
    fun getCurrentAudioMetaData(playbackState: PlaybackStateCompat, mediaMetadata: MediaMetadataCompat): AudioMediaData.MetaData?
}