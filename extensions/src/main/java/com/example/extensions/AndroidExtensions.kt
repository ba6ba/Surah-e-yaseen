package com.example.extensions

import android.os.Build

fun isVersionLowerThan(version : Int = 24) = Build.VERSION.SDK_INT < version

fun isVersionGreaterThan(version : Int = 24, equals : Boolean = false) =
    if (equals) Build.VERSION.SDK_INT.greaterThanEqualsTo(version) else Build.VERSION.SDK_INT.greaterThan(version)