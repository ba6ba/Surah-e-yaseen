package com.example.listpager

import androidx.viewpager2.widget.ViewPager2
import com.example.extensions.isTrue

fun ViewPager2.moveToNextItem(smoothScroll : Boolean = true) = kotlin.run {
    (nextItem <= adapter?.itemCount ?: 0).isTrue {
        setCurrentItem(nextItem, smoothScroll)
    }
}

fun ViewPager2.moveToPreviousItem(smoothScroll : Boolean = true) = kotlin.run {
    (previousItem >= initialItem).isTrue {
        setCurrentItem(previousItem, smoothScroll)
    }
}

val ViewPager2.nextItem
    get() = currentItem.plus(1)

val ViewPager2.previousItem
    get() = currentItem.minus(1)

val ViewPager2.initialItem
    get() = 0