package com.example.audioplayer

enum class AudioPlayerState {
    PLAY,
    STOP
}

fun AudioPlayerState.toggle() = if (AudioPlayerState.PLAY == this) AudioPlayerState.STOP else AudioPlayerState.PLAY