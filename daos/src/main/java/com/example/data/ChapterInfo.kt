package com.example.data

import com.squareup.moshi.Json

data class ChapterInfo(
    var id : Int?,
    @field:Json(name = "chapter_number")
    var chapterNumber : Int?,
    @field:Json(name = "bismillah_pre")
    var bismillahPre : Boolean?,
    @field:Json(name = "revelation_order")
    var revelationOrder : Int?,
    @field:Json(name = "revelation_place")
    var revelationPlace : String?,
    @field:Json(name = "name_complex")
    var nameComplex : String?,
    @field:Json(name = "name_arabic")
    var nameArabic : String?,
    @field:Json(name = "name_simple")
    var nameSimple : String?,
    @field:Json(name = "verses_count")
    var numberOfVerses : Int?
)