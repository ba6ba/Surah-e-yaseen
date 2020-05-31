package com.example.tilawat

import com.example.data.reciters.ReciterWrapper

data class TilawatChapterData(
    var numberOfVerses: Int = -1, var revelationPlace: String = "",
    var surahNumber: Int = -1, var surahNameEnglish: String = "",
    var surahNameArabic: String = "", var reciter: ReciterWrapper? = null,
    var firstVerseId: Int = 0
)