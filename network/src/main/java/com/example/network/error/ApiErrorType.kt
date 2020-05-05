package com.example.network.error

enum class ApiErrorType(var message : String) {
    TIMEOUT("Please check your internet connection! It seems bit disturbed"),
    NETWORK("Server Down"),
    UNKNOWN("Technical Error!\n We are working on it.")
}