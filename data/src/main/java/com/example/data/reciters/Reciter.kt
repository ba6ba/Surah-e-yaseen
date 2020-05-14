package com.example.data.reciters

import com.example.data.hasData
import com.squareup.moshi.Json

data class Reciter(
    var id: Int,
    var style: String?,
    @field:Json(name = "reciter_name_eng")
    var reciterEngName: String,
    @field:Json(name = "reciter_name_translated")
    var reciterTranslatedName: String
)

fun Reciter.toReciterWrapper() = ReciterWrapper(this)

fun List<Reciter>?.toWrapperList() = this?.map { it.toReciterWrapper() }?.hasData {
    it.first().selected = true
}