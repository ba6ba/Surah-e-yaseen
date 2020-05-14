package com.example.data

import com.example.data.reciters.ReciterWrapper

fun Any?.isNotNull() = this != null

fun <T> List<T>?.hasData(list: (List<T>) -> Unit) : List<T>? =
    if(isNullOrEmpty().not()) {
        list(this!!)
        this
    }
    else this!!