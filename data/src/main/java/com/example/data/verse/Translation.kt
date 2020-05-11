package com.example.data.verse

import com.squareup.moshi.Json

data class Translation(
    var id : Int?,
    @field:Json(name = "resource_id")
    var translatorId : Int?,
    @field:Json(name = "language_name")
    var languageName : String?,
    var text : String?,
    @field:Json(name = "resource_name")
    var translatorName : String?
    )