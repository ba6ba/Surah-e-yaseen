package com.example.extensions

import android.widget.TextView

fun TextView?.showAndApplyIfNotEmpty(text : String? = "") {
    if (text.isNullOrEmpty().not()) {
        this?.text = text
        this?.show()
    } else this?.hide()
}

fun TextView?.setTextIfEmptyOrNull(text : String?) {
    if(text.isNullOrEmpty()?.not()) {
        this?.text = text
    }
}