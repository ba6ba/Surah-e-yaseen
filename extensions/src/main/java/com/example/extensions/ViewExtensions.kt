package com.example.extensions

import android.view.View

fun View.show() = run {
    visibility = View.VISIBLE
    this
}

fun View.invisible() = run {
    visibility = View.INVISIBLE
    this
}

fun View.hide() = run {
    visibility = View.GONE
    this
}

fun View.visibility(show: Boolean, invisible: Boolean = false) = run {
    if (show) show() else if (invisible) invisible() else hide()
}

inline fun View.visibility(
    show: Boolean, invisible: Boolean = false,
    noinline showed: ((View) -> Unit)? = null, noinline hide: ((View) -> Unit)? = null
) = run {
        if (show) show() else if (invisible) invisible() else hide()
        show.checkForTrue {
            showed.nonNull { invoke(this@visibility) }
        } ?: hide.nonNull { invoke(this@visibility) }
    }