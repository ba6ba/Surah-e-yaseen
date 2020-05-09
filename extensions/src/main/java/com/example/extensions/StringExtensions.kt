package com.example.extensions

import android.os.Build
import android.text.Html

fun String?.toHtml() = if (isVersionLowerThan(Build.VERSION_CODES.N)) Html.fromHtml(this)
else Html.fromHtml(this, Html.FROM_HTML_MODE_COMPACT)