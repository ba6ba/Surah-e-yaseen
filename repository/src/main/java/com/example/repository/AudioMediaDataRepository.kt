package com.example.repository

import com.example.data.audio.AudioApiResponse
import com.example.data.audio.AudioHelperData
import com.example.data.audio.AudioMediaData
import com.example.network.ApiResponse
import com.example.network.repository.AudioNetworkRepository
import com.example.repository.base.Repository
import com.example.repository.base.Resource
import com.example.repository.dataprovider.IAudioMediaDataMapper
import com.example.storage.dao.AudioMediaDataDao

class AudioMediaDataRepository constructor(
    private val audioMediaDataDao: AudioMediaDataDao,
    private val audioNetworkRepository: AudioNetworkRepository,
    private val audioMediaDataMapper: IAudioMediaDataMapper
) {

    suspend fun fetchDataFor(audioHelperData: AudioHelperData, callback: suspend Resource<List<AudioMediaData>>.() -> Unit) {
        object : Repository<List<AudioMediaData>, AudioApiResponse>() {
            override suspend fun loadFromDb(): List<AudioMediaData> {
                return audioMediaDataDao.getAll() ?: emptyList()
            }

            override suspend fun fetchFromRemote(): ApiResponse<AudioApiResponse>? {
                return audioNetworkRepository.getAudioClipData1(audioHelperData.audioClipModel)
            }

            override fun needToFetchFromRemote(data: List<AudioMediaData>): Boolean {
                return data.find { it.id == audioHelperData.currentAudioId } == null
            }

            override suspend fun postResult(resource: Resource<List<AudioMediaData>>) {
                callback(resource)
            }

            override suspend fun saveRemoteDataIntoDb(data: AudioApiResponse?) {
                audioMediaDataMapper.mapAudioApiResponseToAudioMediaData(data, audioHelperData) {
                    audioMediaDataDao.add(this)
                }
            }
        }
    }
}