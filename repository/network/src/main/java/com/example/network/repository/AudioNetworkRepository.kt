package com.example.network.repository

import com.example.daos.AudioApiResponse
import com.example.daos.AudioClipModel
import com.example.network.ApiResponse
import com.example.network.error.ErrorHandler
import com.example.network.services.AudioService

class AudioNetworkRepository(private val audioService : AudioService) : NetworkRepository() {

    suspend fun getAudioClipData(audioClipModel: AudioClipModel, errorHandler: ErrorHandler) : AudioApiResponse? =
        makeApiCall(errorHandler) {
            audioService.getAudio(audioClipModel.chapterId, audioClipModel.verseId, audioClipModel.reciterId)
        }

    suspend fun getAudioClipData1(audioClipModel: AudioClipModel) : ApiResponse<AudioApiResponse> =
        apiCall {
            audioService.getAudio1(audioClipModel.chapterId, audioClipModel.verseId, audioClipModel.reciterId)
        }
}