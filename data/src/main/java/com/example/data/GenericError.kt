package com.example.data

data class GenericError(var severity: Severity, var message: String) {
    companion object {
        fun lowSeverityError(message: String) =
            GenericError(Severity.LOW, message)

        fun highSeverityError(message: String) =
            GenericError(Severity.HIGH, message)
    }

    enum class Severity {
        LOW,
        HIGH
    }
}