package com.example.network.error

enum class ErrorType(var message : String) {
    INVALID_AUDIO_DATA("Oh! This audio clip having issues in playing. Can you please try again or play next."),
    TIMEOUT("Please check your internet connection! It seems bit disturbed"),
    NETWORK("Server Down"),
    DATABASE("DB Error"),
    UNKNOWN("Technical Error!\n We are working on it."),
    NONE("")
}