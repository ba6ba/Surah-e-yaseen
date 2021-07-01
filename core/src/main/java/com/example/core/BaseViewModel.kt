package com.example.core

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.GenericError
import com.example.network.error.ErrorType
import com.example.network.error.ErrorHandler
import com.example.data.GenericError.Companion.lowSeverityError
import com.example.data.GenericError.Companion.highSeverityError

abstract class BaseViewModel : ViewModel(), ErrorHandler {

    val errorLiveData : MutableLiveData<GenericError> = MutableLiveData()

    override fun onError(error: String) {
        errorLiveData.postValue(lowSeverityError(error))
    }

    override fun onError(error: ErrorType) {
        when(error) {
            ErrorType.UNKNOWN -> errorLiveData.postValue(lowSeverityError(error.message))
            ErrorType.TIMEOUT -> errorLiveData.postValue(highSeverityError(error.message))
            ErrorType.NETWORK -> errorLiveData.postValue(highSeverityError(error.message))
            ErrorType.DATABASE -> errorLiveData.postValue(lowSeverityError(error.message))
            ErrorType.INVALID_AUDIO_DATA -> errorLiveData.postValue(highSeverityError(error.message))
            else -> {}
        }
    }

}