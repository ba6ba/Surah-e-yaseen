package com.example.tilawat.dataprovider

import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.PlaybackStateCompat
import com.example.data.audio.Audio
import com.example.data.audio.AudioHelperData
import com.example.data.audio.AudioMediaData
import com.example.data.audio.ServiceMetaData
import com.example.data.lastsaved.LastSavedAudio

interface IAudioData {
    val authorData : AudioMediaData.AuthorData
    val mediaMetadata : AudioMediaData.MediaMetaData
    fun getAll() : List<AudioMediaData>
    fun getCurrentPlayingMediaMetadata() : AudioMediaData.MediaMetaData
    fun getCurrentPlayingMetadata() : AudioMediaData.MetaData?
    fun get(index : Int) : AudioMediaData
    fun canPlayFromLocalList(verseNumber: Int, fetchFromRepository : (Boolean) -> Unit)
    fun transformMediaItemDataToAudioMediaData(children: MutableList<MediaBrowserCompat.MediaItem>, itemToPlay: AudioMediaData.() -> Unit)
    fun getCurrentAudioMetaData(playbackState: PlaybackStateCompat): AudioMediaData.MetaData?
    fun updateCurrentVerse(verseNumber: Number)
    fun buildAudioHelperData(verseNumber: Int) : AudioHelperData
    fun updateList(list: List<AudioMediaData>)
    fun mapMetaDataFromList(): List<ServiceMetaData>
    fun getLastSavedAudioData() : LastSavedAudio?
}