package com.example.network.repository

import com.example.data.audio.AudioApiResponse
import com.example.data.audio.AudioClipModel
import com.example.network.ApiResponse
import com.example.network.error.ErrorHandler
import com.example.network.services.AudioService

class AudioNetworkRepository(private val audioService : AudioService) : NetworkRepository() {

    suspend fun getAudioClipData(audioClipModel: AudioClipModel) : ApiResponse<AudioApiResponse> =
        apiCall {
            audioService.getAudio(audioClipModel.chapterId, audioClipModel.verseId, audioClipModel.reciterId)
        }
}