package com.example.network

object LanguageProvider {

    private const val URDU = "urdu"

    val languageCodeMap = mapOf(URDU to "ur")

    val getDefaultLanguageCode : String
        get() = languageCodeMap[URDU]!!
}