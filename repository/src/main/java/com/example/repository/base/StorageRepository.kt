package com.example.repository.base

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.liveData
import com.example.extensions.nonNull
import com.example.network.ApiResponse
import com.example.network.IODispatcher
import com.example.network.MainDispatcher
import com.example.network.error.ErrorType
import kotlinx.coroutines.withContext
import timber.log.Timber

abstract class StorageRepository<ResultType, RequestType>
internal constructor() {

    private val result: MediatorLiveData<Resource<ResultType>> = MediatorLiveData()

    init {
        Timber.d("Storage repository init")
        liveData<ResultType> {
            val loadedFromDb = loadFromDb()
            if (shouldFetch(loadedFromDb.value)) {
                result.postValue(Resource.loading(null))
                fetchFromRemote(loadedFromDb)
            } else {
                loadedFromDb.value?.let {
                    setValue(newValue = Resource.success(it))
                }
            }
        }
    }

    private suspend fun fetchFromRemote(loadedFromDb: LiveData<ResultType>) {
        val apiResponse = fetchFromRemote()
        apiResponse.nonNull {
            if (isSuccessful) {
                data?.let { nonNullData ->
                    withContext(IODispatcher) {
                        saveFetchedData(nonNullData)
                        val dataFromDb = loadFromDb()
                        dataFromDb.value.nonNull {
                            this@StorageRepository.setValue(Resource.success(this))
                        }
                    }
                }
            } else {
                withContext(MainDispatcher) {
                    val errorType = ErrorType.NETWORK.apply { message = this@nonNull.message ?: message }
                    onFetchFailed(errorType)
                    loadedFromDb.value.nonNull {
                        setValue(Resource.error(errorType, this))
                    }
                }
            }
        }
    }

    @MainThread
    private fun setValue(newValue: Resource<ResultType>) {
        result.value = newValue
    }

    fun asLiveData(): LiveData<Resource<ResultType>> {
        return result
    }

    @WorkerThread
    abstract suspend fun loadFromDb(): LiveData<ResultType>

    @WorkerThread
    abstract suspend fun saveFetchedData(data: RequestType?)

    @MainThread
    abstract suspend fun fetchFromRemote(): ApiResponse<RequestType>

    @MainThread
    abstract fun shouldFetch(data: ResultType?): Boolean

    @MainThread
    abstract fun onFetchFailed(errorType: ErrorType)
}