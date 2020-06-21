package com.example.daos

data class AudioHelperData(
    var title: String,
    var numberOfAudioItems: Int,
    var currentNumber : String,
    var authorData: AudioMediaData.AuthorData,
    var audioClipModel: AudioClipModel
)