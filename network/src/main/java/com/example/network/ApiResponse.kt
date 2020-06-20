package com.example.network

import retrofit2.Response
import timber.log.Timber

@Suppress("MemberVisibilityCanBePrivate")
class ApiResponse<T> {
    val code : Int
    val data : T?
    val message : String?

    val isSuccessful : Boolean
        get() = code in 200..300
    val isFailed : Boolean

    constructor(throwable: Throwable) {
        code = 500
        message = throwable.localizedMessage
        data = null
        isFailed = true
    }

    constructor(response : Response<T>) {
        code = response.code()
        if (response.isSuccessful) {
            data = response.body()
            message = null
            isFailed = false
        } else {
            var errorMessage : String? = null
            try {
                errorMessage = response.errorBody()?.toString()
            } catch (e : Exception) {
                Timber.e(e.localizedMessage)
            }
            errorMessage?.apply {
                if (isNullOrEmpty() || trim { it <= ' ' }.isEmpty()) {
                    errorMessage = response.message()
                }
            }
            message = errorMessage
            isFailed = true
            data = null
        }

    }
}