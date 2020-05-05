package com.example.data.verse

data class Content(
    var position : Int,
    var audio : Audio,
    var translation: Translation,
    var transliteration: Translation
) : BaseVerse()