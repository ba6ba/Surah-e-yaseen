package com.example.repository.dataprovider

import com.example.data.audio.*
import com.example.extensions.getIdFromUrl
import com.example.extensions.nonNull
import com.example.extensions.toSeconds
import com.example.extensions.toTimeStamp
import com.example.repository.R
import com.example.tilawat.dataprovider.AudioMediaIdGenerator

class AudioMediaDataMapper : IAudioMediaDataMapper {

    private val data: AudioMediaData.Data by lazy {
        AudioMediaData.Data()
    }

    private val metadata: AudioMediaData.MetaData by lazy {
        AudioMediaData.MetaData()
    }

    private val imageMetadata: AudioMediaData.ImageMetaData by lazy {
        AudioMediaData.ImageMetaData()
    }


    private fun createAudioMediaData(audio: Audio, audioHelperData: AudioHelperData) = AudioMediaData.build {
        id = AudioMediaIdGenerator.generate(audioHelperData.currentNumber, audio.url.getIdFromUrl())
        title = audioHelperData.title
        authorData = audioHelperData.authorData
        imageMetaData = imageMetadata.copy(imageDrawableRes = R.drawable.splash_logo)
        data = this@AudioMediaDataMapper.data.copy(
            totalNumber = audioHelperData.numberOfAudioItems, audioId = audio.url.getIdFromUrl(),
            id = audioHelperData.currentNumber
        )
        metaData = metadata.copy(
            playbackState = getPlayingState,
            url = audio.url, format = audio.format,
            audioDuration = audio.duration.toSeconds,
            displayableDuration = audio.duration.toSeconds.toTimeStamp()
        )
    }

    private val getPlayingState: State
        get() = State.PLAYING
            .apply {
                imageRes = R.drawable.ic_play_icon
            }

    override suspend fun mapAudioApiResponseToAudioMediaData(
        audioApiResponse: AudioApiResponse?,
        audioHelperData: AudioHelperData,
        audioMediaData: suspend AudioMediaData.() -> Unit
    ) {

        audioApiResponse.nonNull {
            audio.nonNull {
                audioMediaData(createAudioMediaData(this, audioHelperData))
            }
        }
    }
}