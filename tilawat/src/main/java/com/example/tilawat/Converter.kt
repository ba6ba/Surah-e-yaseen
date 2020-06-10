package com.example.tilawat

import com.example.data.Chapter

fun Chapter?.toTilawatChapterData() =
    TilawatChapterData(
        numberOfVerses = this?.numberOfVerses!!, revelationPlace = revelationPlace!!.capitalize(),
        surahNumber = number!!, surahNameEnglish = name!!, surahNameArabic = arabicName!!
    )