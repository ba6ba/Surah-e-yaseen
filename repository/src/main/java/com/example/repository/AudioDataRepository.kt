package com.example.repository

import androidx.lifecycle.LiveData
import com.example.daos.AudioApiResponse
import com.example.daos.AudioHelperData
import com.example.daos.AudioMediaData
import com.example.network.ApiResponse
import com.example.network.error.ErrorHandler
import com.example.network.error.ErrorType
import com.example.network.repository.AudioNetworkRepository
import com.example.repository.base.Resource
import com.example.repository.base.StorageRepository
import com.example.repository.providers.AudioDataProvider
import com.example.storage.dao.AudioMediaDataDao

class AudioDataRepository constructor(
    private val audioMediaDataDao: AudioMediaDataDao,
    private val audioDataProvider: AudioDataProvider,
    private val audioNetworkRepository: AudioNetworkRepository
) {

    fun loadAudioData(helperData: AudioHelperData, errorHandler: ErrorHandler): LiveData<Resource<List<AudioMediaData>>> {
        return object : StorageRepository<List<AudioMediaData>, AudioApiResponse>() {

            override fun shouldFetch(data: List<AudioMediaData>?): Boolean {
                return data?.find { it.id == helperData.currentNumber } == null
            }

            override fun onFetchFailed(errorType: ErrorType) {
                errorHandler.onError(errorType)
            }

            override suspend fun loadFromDb(): LiveData<List<AudioMediaData>> {
                return audioMediaDataDao.getAll()
            }

            override suspend fun saveFetchedData(data: AudioApiResponse?) {
                audioDataProvider.loadAudioDataWithHelperData(data, helperData)?.let {
                    audioMediaDataDao.add(it)
                }
            }

            override suspend fun fetchFromRemote(): ApiResponse<AudioApiResponse> {
                return audioNetworkRepository.getAudioClipData1(helperData.audioClipModel)
            }

        }.asLiveData()
    }
}