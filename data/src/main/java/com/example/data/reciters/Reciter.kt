package com.example.data.reciters

import com.example.data.audio.AudioMediaData
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

val defaultReciter
    get() = Reciter(
        id = 7, reciterEngName = "Mishary Rashid Al Afasy",
        reciterTranslatedName = "Mishary Rashid Al Afasy", style = ""
    )

val Reciter.toAuthorData
    get() = AudioMediaData.AuthorData(id = id, name = reciterEngName, detail = style)