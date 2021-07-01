package com.example.repository

import com.example.data.audio.AudioApiResponse
import com.example.data.audio.AudioHelperData
import com.example.data.audio.AudioMediaData
import com.example.data.hasData
import com.example.network.ApiResponse
import com.example.network.IODispatcher
import com.example.network.repository.AudioNetworkRepository
import com.example.repository.base.Repository
import com.example.repository.base.Resource
import com.example.repository.dataprovider.IAudioMediaDataMapper
import com.example.storage.dao.AudioMediaDataDao
import kotlinx.coroutines.withContext

class AudioMediaDataRepository constructor(
    private val audioMediaDataDao: AudioMediaDataDao,
    private val audioNetworkRepository: AudioNetworkRepository,
    private val audioMediaDataMapper: IAudioMediaDataMapper
) {

    suspend fun fetchData(audioHelperData: AudioHelperData, callback: suspend Resource<List<AudioMediaData>>.() -> Unit) {
        object : Repository<List<AudioMediaData>, AudioApiResponse>() {
            override suspend fun loadFromDb(): List<AudioMediaData> {
                return audioMediaDataDao.getAll() ?: emptyList()
            }

            override suspend fun fetchFromRemote(): ApiResponse<AudioApiResponse>? {
                return audioNetworkRepository.getAudioClipData(audioHelperData.audioClipModel)
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

    suspend fun updateAuthorForAll(authorData: AudioMediaData.AuthorData) {
        val allAudioData = audioMediaDataDao.getAll()
        if (allAudioData.hasData()) {
            withContext(IODispatcher) {
                allAudioData?.apply {
                    map { it.authorData = authorData }
                }
            }
        }
    }

    suspend fun updateAuthor(audioId : String, authorData: AudioMediaData.AuthorData) {
        audioMediaDataDao.update(authorData, audioId)
    }
}