package com.example.extensions

import android.net.Uri
import android.os.Build
import android.text.Html
import android.text.Spanned
import java.util.*

const val EMPTY_STRING = ""

fun String?.toHtml(): Spanned? = if (isVersionLowerThan(Build.VERSION_CODES.N)) Html.fromHtml(this)
else Html.fromHtml(this, Html.FROM_HTML_MODE_COMPACT)

fun String?.toUri(): Uri = this?.let { Uri.parse(it) } ?: Uri.EMPTY

fun String?.containsCaseInsensitive(other: String?) =
    if (this == null && other == null) {
        true
    } else if (this != null && other != null) {
        toLowerCase(Locale.ROOT).contains(other.toLowerCase(Locale.ROOT))
    } else {
        false
    }

fun String?.asFormatted(format : String) = String.format(format, this)
