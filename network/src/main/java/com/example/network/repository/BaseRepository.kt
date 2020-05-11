package com.example.network.repository

import com.example.network.IODispatcher
import com.example.network.MainDispatcher
import com.example.network.error.ApiErrorType.*
import com.example.network.error.ErrorHandler
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import java.net.SocketTimeoutException

abstract class BaseRepository {

    companion object {
        private const val MESSAGE_KEY = "message"
        private const val ERROR_KEY = "error"
    }

    suspend inline fun <T> makeApiCall(errorHandler: ErrorHandler?, crossinline apiFunction: suspend () -> T): T? {
        return try {
            withContext(IODispatcher) {
                apiFunction()
            }
        } catch (exception: Exception) {
            errorHandling(errorHandler, exception)
            null
        }
    }

    suspend fun errorHandling(errorHandler: ErrorHandler?, e: Exception){
        Timber.e(e.localizedMessage?.toString().toString())
        errorHandler?.let {
            withContext(MainDispatcher) {
                when (e) {
                    is HttpException -> errorHandler.onError(getErrorMessage(e.response()?.errorBody()))
                    is SocketTimeoutException -> errorHandler.onError(TIMEOUT)
                    is IOException -> errorHandler.onError(NETWORK)
                    else -> errorHandler.onError(UNKNOWN)
                }
            }
        }
    }

    private fun getErrorMessage(response: ResponseBody?): String {
        return response?.run {
            try {
                val jsonObject = JSONObject(response.string())
                when {
                    jsonObject.has(MESSAGE_KEY) -> jsonObject.getString(
                        MESSAGE_KEY
                    )
                    jsonObject.has(ERROR_KEY) -> jsonObject.getString(
                        ERROR_KEY
                    )
                    else -> UNKNOWN.message
                }
            } catch (e: Exception) {
                UNKNOWN.message
            }
        } ?: UNKNOWN.message
    }
}