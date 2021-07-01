package com.example.repository.dataprovider

import com.example.data.audio.AudioApiResponse
import com.example.data.audio.AudioHelperData
import com.example.data.audio.AudioMediaData

interface IAudioMediaDataMapper {
    suspend fun mapAudioApiResponseToAudioMediaData(
        audioApiResponse: AudioApiResponse?, audioHelperData: AudioHelperData,
        audioMediaData: suspend AudioMediaData.() -> Unit
    )
}