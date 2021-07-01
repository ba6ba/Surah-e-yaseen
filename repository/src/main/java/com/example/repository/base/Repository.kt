package com.example.repository.base

import com.example.extensions.isTrue
import com.example.extensions.nonNull
import com.example.extensions.notNull
import com.example.network.ApiResponse
import com.example.network.IODispatcher
import com.example.network.error.ErrorType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

abstract class Repository<ResultType, RequestType>
internal constructor() : CoroutineScope by MainScope() {

    init {
        launch(IODispatcher) {
            postResult(Resource.loading(null))
            val loadFromDb = loadFromDb()
            loadFromDb.nonNull {
                isTrue(needToFetchFromRemote(this)) {
                    launch(IODispatcher) {
                        val remoteData = fetchFromRemote()
                        notNull(remoteData) {
                            launch(IODispatcher) {
                                saveRemoteDataIntoDb(remoteData?.data)
                                val newData = loadFromDb()
                                newData.nonNull {
                                    postResult(Resource.success(newData))
                                } ?: postResult(Resource.error(ErrorType.DATABASE, null))
                            }
                        } ?: postResult(Resource.error(ErrorType.NETWORK, loadFromDb))
                    }
                } ?: postResult(Resource.success(loadFromDb))
            } ?: postResult(Resource.error(ErrorType.DATABASE, null))
        }
    }

    abstract suspend fun loadFromDb(): ResultType

    abstract suspend fun fetchFromRemote(): ApiResponse<RequestType>?

    abstract suspend fun saveRemoteDataIntoDb(data: RequestType?)

    abstract fun needToFetchFromRemote(data: ResultType): Boolean

    abstract suspend fun postResult(resource: Resource<ResultType>)
}