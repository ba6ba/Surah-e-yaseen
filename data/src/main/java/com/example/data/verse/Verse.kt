package com.example.data.verse

import com.squareup.moshi.Json

data class Verse(
    @field:Json(name = "chapter_id")
    var chapterId : Int?,
    @field:Json(name = "juz_number")
    var juzNumber : String?,
    @field:Json(name = "hizb_number")
    var hizbNumber : String?,
    @field:Json(name = "rub_number")
    var rubNumber : String?,
    @field:Json(name = "sajdah")
    private var sajdah : String?,
    @field:Json(name = "sajdah_number")
    var sajdahNumber : String?,
    @field:Json(name = "words")
    var contents : List<Content>?,
    var translations: List<Translation>?,
    var showSajdah : Boolean = sajdah.isNullOrEmpty().not() && sajdah == "required"
) : BaseVerse()

fun Verse?.translation(translatorId : Int) = this?.translations?.filter { it.translatorId == translatorId }?.
    joinToString(separator = " ") { it.text ?: "" }