package com.example.data

fun Any?.isNotNull() = this != null

fun <T> List<T>?.hasData(list: (List<T>) -> Unit) : List<T>? =
    if(isNullOrEmpty().not()) {
        list(this!!)
        this
    }
    else this!!

fun <T> List<T>?.hasData() : Boolean = isNullOrEmpty().not()