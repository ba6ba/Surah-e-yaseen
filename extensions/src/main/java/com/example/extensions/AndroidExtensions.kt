package com.example.extensions

import android.os.Build

fun isVersionLowerThan(version : Int = 24) = Build.VERSION.SDK_INT < version