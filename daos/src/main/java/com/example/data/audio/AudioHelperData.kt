package com.example.data.audio

data class AudioHelperData(
    var title: String,
    var numberOfAudioItems: Int,
    var currentAudioId : String,
    var currentNumber : Int,
    var authorData: AudioMediaData.AuthorData,
    var audioClipModel: AudioClipModel
)