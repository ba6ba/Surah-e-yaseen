package com.example.listpager

import androidx.viewpager2.widget.ViewPager2

val transformer = ViewPager2.PageTransformer { page, position ->
    val scalingStart = 1 - 0.7f
    if (position >= 0) {
        val w = page.width
        val scaleFactor: Float = 1 - scalingStart * position
        page.alpha = 1 - position
        page.scaleX = scaleFactor
        page.scaleY = scaleFactor
        page.translationX = w * (1 - position) - w
    }
//    page.apply {
//        alpha = 0.25f + (1 - abs(position))
//    }
}