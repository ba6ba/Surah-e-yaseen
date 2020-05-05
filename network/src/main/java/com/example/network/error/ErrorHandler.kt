package com.example.network.error

interface ErrorHandler {
    fun onError(error : String)
    fun onError(error : ApiErrorType)
}