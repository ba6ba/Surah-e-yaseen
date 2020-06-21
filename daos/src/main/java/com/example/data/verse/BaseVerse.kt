package com.example.data.verse

import com.squareup.moshi.Json

abstract class BaseVerse (
    var id : Int ? = null,
    @field:Json(name = "verse_number")
    var verseNumber : Int ? = null,
    @field:Json(name = "verse_key")
    var verseKey : String ? = null,
    @field:Json(name = "text_madani")
    var madniText : String ? = null,
    @field:Json(name = "text_indopak")
    var indoPakText : String ? = null,
    @field:Json(name = "text_simple")
    var simpleText : String ? = null,
    @field:Json(name = "page_number")
    var pageNumber : String ? = null
)