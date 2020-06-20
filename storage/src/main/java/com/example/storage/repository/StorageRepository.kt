package com.example.storage.repository

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.extensions.isTrue
import com.example.extensions.nonNull
import com.example.network.ApiResponse
import com.example.network.error.ErrorType
import com.example.storage.utils.AppExecutors
import timber.log.Timber

abstract class StorageRepository<ResultType, RequestType>
internal constructor() {

    private val result: MediatorLiveData<Resource<ResultType>> = MediatorLiveData()

    init {
        Timber.d("Storage repository init")
        AppExecutors.diskIO.execute {
            val loadedFromDb = loadFromDb()
            result.addSource(loadedFromDb) { data ->
                result.removeSource(loadedFromDb)
                shouldFetch(data).isTrue {
                    result.postValue(Resource.loading(null))
                    fetchFromRemote(loadedFromDb)
                } ?: kotlin.run {
                    result.addSource(loadedFromDb) { newData ->
                        setValue(newValue = Resource.success(newData))
                    }
                }
            }
        }
    }

    private fun fetchFromRemote(loadedFromDb: LiveData<ResultType>) {
        val apiResponse = fetchFromRemote()
        result.addSource(apiResponse) { response ->
            response.nonNull {
                isSuccessful.isTrue {
                    AppExecutors.diskIO.execute {
                        response.data?.let {
                            saveFetchedData(it)
                            val loaded = loadFromDb()
                            result.addSource(loaded) { newData ->
                                newData?.let { result ->
                                    setValue(Resource.success(result))
                                }
                            }
                        }
                    }
                } ?: kotlin.run {
                    AppExecutors.mainIO.execute {
                        result.removeSource(loadedFromDb)
                        val errorType = ErrorType.NETWORK.apply { message = response.message ?: message }
                        onFetchFailed(errorType)
                        result.addSource(loadedFromDb) { newData ->
                            setValue(Resource.error(errorType, newData))
                        }
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
    abstract fun loadFromDb(): LiveData<ResultType>

    @WorkerThread
    abstract fun saveFetchedData(data: RequestType?)

    @MainThread
    abstract fun fetchFromRemote(): LiveData<ApiResponse<RequestType>>

    @MainThread
    abstract fun shouldFetch(data: ResultType?): Boolean

    @MainThread
    abstract fun onFetchFailed(errorType: ErrorType)
}