package com.example.storage.repository

import androidx.lifecycle.LiveData
import com.example.network.ApiResponse
import com.example.network.error.ErrorType
import com.example.storage.dao.AudioMediaDataDao
import com.example.storage.model.AudioMediaData
import com.example.storage.model.ImageMetaData

class AudioDataRepository constructor(
    private val audioMediaDataDao: AudioMediaDataDao
) {

    fun loadAudioData(): LiveData<Resource<List<AudioMediaData>>> {
        return object : StorageRepository<List<AudioMediaData>, ImageMetaData>() {
            override fun loadFromDb(): LiveData<List<AudioMediaData>> {
                return audioMediaDataDao.getAll()
            }

            override fun saveFetchedData(data: ImageMetaData?) {
//                audioMediaDataDao.addAll()
            }

            override fun fetchFromRemote(): LiveData<ApiResponse<ImageMetaData>> {
                TODO("Not yet implemented")
            }

            override fun shouldFetch(data: List<AudioMediaData>?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onFetchFailed(errorType: ErrorType) {
                TODO("Not yet implemented")
            }

        }.asLiveData()
    }
}