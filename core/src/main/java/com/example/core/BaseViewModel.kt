package com.example.core

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.GenericError
import com.example.network.error.ApiErrorType
import com.example.network.error.ErrorHandler
import com.example.data.GenericError.Companion.lowSeverityError
import com.example.data.GenericError.Companion.highSeverityError

abstract class BaseViewModel : ViewModel(), ErrorHandler {

    val errorLiveData : MutableLiveData<GenericError> = MutableLiveData()

    override fun onError(error: String) {
        errorLiveData.postValue(lowSeverityError(error))
    }

    override fun onError(error: ApiErrorType) {
        when(error) {
            ApiErrorType.UNKNOWN -> errorLiveData.postValue(lowSeverityError(error.message))
            ApiErrorType.TIMEOUT -> errorLiveData.postValue(highSeverityError(error.message))
            ApiErrorType.NETWORK -> errorLiveData.postValue(highSeverityError(error.message))
        }
    }

}