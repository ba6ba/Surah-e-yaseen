package com.example.tilawat

import com.example.audioplayer.AudioPlayerMetaData
import com.example.data.Chapter
import com.example.media.media.source.AudioClipData

fun Chapter?.toTilawatChapterData() =
    TilawatChapterData(
        numberOfVerses = this?.numberOfVerses!!, revelationPlace = revelationPlace!!.capitalize(),
        surahNumber = number!!, surahNameEnglish = name!!, surahNameArabic = arabicName!!
    )

fun AudioClipData.Metadata.toAudioPlayerMetaData() =
    AudioPlayerMetaData(
        author = author, subTitle = subTitle, duration = duration,
        displayableDuration = displayableDuration, playing = playing,
        title = title, imageUri = imageUri, image = image
)