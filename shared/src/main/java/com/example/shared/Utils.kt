package com.example.shared

const val SURAH_E_YASEEN = "SURAH_E_YASEEN"
const val SURAH_E_FATIHA = "SURAH_E_YASEEN"

private val chaptersData = mapOf(
    SURAH_E_FATIHA to 1,
    SURAH_E_YASEEN to 36
)

fun getChapterNumber(name : String) = chaptersData[name]!!

fun getSurahYaseen() = getChapterNumber(SURAH_E_YASEEN)