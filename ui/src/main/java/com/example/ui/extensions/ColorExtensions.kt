package com.example.ui.extensions

import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable

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

fun RippleDrawable.getCurrentDrawableColor() = kotlin.run {
    (getDrawable(0).current as GradientDrawable).color?.defaultColor
}