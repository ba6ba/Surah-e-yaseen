package com.example.daos

import java.io.Serializable

data class Audio(
    var url: String,
    var duration: Int,
    var segments: List<List<Int>> = emptyList(),
    var format: String
) : Serializable