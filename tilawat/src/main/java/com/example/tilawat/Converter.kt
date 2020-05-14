package com.example.tilawat

import com.example.data.Chapter
import java.util.*

fun Chapter?.toTilawatChapterData() =
    TilawatChapterData(
        numberOfVerses = this?.numberOfVerses!!, revelationPlace = revelationPlace!!.capitalize(),
        surahNumber = number!!, surahNameEnglish = name!!, surahNameArabic = arabicName!!
    )