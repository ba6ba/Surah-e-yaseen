package com.example.tilawat

import com.example.data.reciters.ReciterWrapper
import com.example.data.reciters.defaultReciter
import com.example.extensions.greaterThan

data class TilawatChapterData(
    var numberOfVerses: Int = -1, var revelationPlace: String = "",
    var surahNumber: Int = -1, var surahNameEnglish: String = "",
    var surahNameArabic: String = "", var reciter: ReciterWrapper? = null,
    var firstVerseId: Int = 0, var currentVerseNumber: Int = 0
)

val TilawatChapterData.reciterId
    get() = reciter?.reciter?.id

val TilawatChapterData.reciterName
    get() = reciter?.reciter?.reciterEngName

val TilawatChapterData.reciterTranslatedName
    get() = reciter?.reciter?.reciterTranslatedName

val TilawatChapterData.getRangeForAudioVerses
    get() = firstVerseId..firstVerseId.plus(numberOfVerses - 1)

val TilawatChapterData.hasValidReciter
    get() = reciterName?.isNotEmpty() == true and (reciterId ?: 0).greaterThan(0)