package com.example.data

data class Audio(
    var url : String,
    var duration : Int,
    var segments : List<List<Int>>,
    var format : String
)