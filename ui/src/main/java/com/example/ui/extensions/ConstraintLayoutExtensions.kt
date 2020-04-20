package com.example.ui.extensions

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet

fun ConstraintLayout.LayoutParams.applyParentConstraints(
    top : Int = ConstraintSet.PARENT_ID, left : Int = ConstraintSet.PARENT_ID,
    bottom : Int = ConstraintSet.PARENT_ID, right : Int = ConstraintSet.PARENT_ID
) = run {
    topToTop = top
    bottomToBottom = bottom
    endToEnd = right
    startToStart = left
    this
}

fun ConstraintLayout.LayoutParams.applyParentVerticalConstraints() = run  {
    topToTop = ConstraintSet.PARENT_ID
    bottomToBottom = ConstraintSet.PARENT_ID
    this
}

fun ConstraintLayout.LayoutParams.applyParentHorizontalConstraints() = run {
    endToEnd = ConstraintSet.PARENT_ID
    startToStart = ConstraintSet.PARENT_ID
    this
}

fun ConstraintLayout.LayoutParams.applyParentTopLeftConstraints() = run {
    topToTop = ConstraintSet.PARENT_ID
    startToStart = ConstraintSet.PARENT_ID
    this
}

fun ConstraintLayout.LayoutParams.applyParentTopRightConstraints() = run {
    topToTop = ConstraintSet.PARENT_ID
    endToEnd = ConstraintSet.PARENT_ID
    this
}

fun ConstraintLayout.LayoutParams.applyTopRightMargins(top : Int, right : Int) = run {
    topMargin = top
    marginEnd = right
    this
}

fun ConstraintLayout.LayoutParams.applyTopLeftMargins(top : Int, start : Int) = run {
    topMargin = top
    marginStart = start
    this
}