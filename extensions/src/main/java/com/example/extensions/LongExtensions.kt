package com.example.extensions

import kotlin.math.floor

private const val UNKNOWN_DURATION = "--:--"
private const val DURATION_FORMAT = "%d:%02d"

fun Long.toTimeStamp() = run {
    val totalSeconds = floor(this / 1E3).toInt()
    val minutes = totalSeconds / 60
    val remainingSeconds = totalSeconds - (minutes * 60)
    if (this < 0) UNKNOWN_DURATION else DURATION_FORMAT.format(minutes, remainingSeconds)
}