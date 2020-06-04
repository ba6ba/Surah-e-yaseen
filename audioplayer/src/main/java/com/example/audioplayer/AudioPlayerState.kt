package com.example.audioplayer

enum class AudioPlayerState {
    PLAYING,
    PAUSE
}

val AudioPlayerState.toggle
    get() = if (AudioPlayerState.PLAYING == this) AudioPlayerState.PAUSE else AudioPlayerState.PLAYING

val Boolean.toAudioPlayerState
    get() = if (this) AudioPlayerState.PLAYING else AudioPlayerState.PAUSE