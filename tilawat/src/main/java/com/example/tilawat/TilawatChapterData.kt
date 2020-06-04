package com.example.tilawat

import com.example.data.reciters.ReciterWrapper
import com.example.data.reciters.defaultReciter

data class TilawatChapterData(
    var numberOfVerses: Int = -1, var revelationPlace: String = "",
    var surahNumber: Int = -1, var surahNameEnglish: String = "",
    var surahNameArabic: String = "", var reciter: ReciterWrapper? = null,
    var firstVerseId: Int = 0
)

val TilawatChapterData.reciterId
    get() = reciter?.reciter?.id ?: defaultReciter.id

val TilawatChapterData.reciterName
    get() = reciter?.reciter?.reciterEngName ?: defaultReciter.reciterEngName

val TilawatChapterData.getRangeForAudioVerses
    get() = firstVerseId..firstVerseId.plus(numberOfVerses - 1)