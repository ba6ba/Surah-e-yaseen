package com.example.data

import com.example.data.verse.Verse

data class Chapter(
    var number: Int?,
    var hasBismillah: Boolean?,
    var numberOfVerses: Int?,
    var arabicName: String?,
    var name: String?,
    var revelationOrder: Int?,
    var revelationPlace: String?,
    var verses: ArrayList<Verse> = arrayListOf()
) {
    constructor(chapterInfo: ChapterInfo?, verses: ArrayList<Verse> = arrayListOf()) :
            this(number = chapterInfo?.chapterNumber,
                hasBismillah = chapterInfo?.bismillahPre,
                numberOfVerses = chapterInfo?.numberOfVerses,
                revelationOrder = chapterInfo?.revelationOrder,
                revelationPlace = chapterInfo?.revelationPlace,
                name = chapterInfo?.nameSimple,
                arabicName = chapterInfo?.nameArabic,
                verses = verses)

    fun isValid() =
        number.isNotNull().and(hasBismillah.isNotNull()).and(numberOfVerses.isNotNull()).
            and(arabicName.isNotNull()).and(name.isNotNull()).and(revelationOrder.isNotNull()).
            and(revelationPlace.isNotNull()).and(verses.isNotNull()).and(verses.isNotEmpty())
}

fun Chapter.get() = if (isValid()) this else null