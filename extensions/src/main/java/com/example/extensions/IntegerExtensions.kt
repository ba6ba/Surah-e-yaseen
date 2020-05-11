package com.example.extensions

const val DEFAULT_INT_VALUE = 0

fun Int.lessThan(value: Int) = this < value

fun Int.lessThanEqualsTo(value: Int) = this <= value

fun Int.greaterThanEqualsTo(value: Int) = this >= value

fun Int.greaterThan(value: Int) = this > value 