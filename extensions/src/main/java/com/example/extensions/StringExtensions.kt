package com.example.extensions

import android.os.Build
import android.text.Html
import android.text.Spanned

fun String?.toHtml(): Spanned? = if (isVersionLowerThan(Build.VERSION_CODES.N)) Html.fromHtml(this)
else Html.fromHtml(this, Html.FROM_HTML_MODE_COMPACT)