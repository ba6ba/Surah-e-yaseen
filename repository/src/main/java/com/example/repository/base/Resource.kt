package com.example.repository.base

import com.example.network.error.ErrorType

class Resource<out T>(
    val status: Status,
    val data: T?,
    val error : ErrorType
) {
    companion object {
        fun <T> success(data : T) = Resource(
            status = Status.SUCCESS,
            data = data,
            error = ErrorType.NONE
        )

        fun <T> error(error: ErrorType, data : T?) = Resource(
            status = Status.FAILURE,
            data = data,
            error = error
        )

        fun <T> loading(data: T?) = Resource(
            status = Status.LOADING,
            data = data,
            error = ErrorType.NONE
        )
    }
}