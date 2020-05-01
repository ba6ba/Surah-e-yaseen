package com.example.ui.extensions

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import com.example.extensions.isVersionLowerThan
import com.example.ui.R

fun RippleDrawable.getColor() : Int = kotlin.run {
    try {
        constantState!!::class.java.getDeclaredField("mColor").run {
            isAccessible = true
            (get(constantState) as ColorStateList).defaultColor
            //TODO 17-april-2020 -- get background color instead of ripple color
        }
    } catch (e: Exception) {
        0
    }
}

@SuppressLint("NewApi")
fun RippleDrawable.getCurrentDrawableColor() = kotlin.run {
    if (isVersionLowerThan(23)) R.color.colorAccent else (getDrawable(0).current as GradientDrawable).color?.defaultColor
}