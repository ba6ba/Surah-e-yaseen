package com.example.ui.extensions

import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import androidx.annotation.*
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.res.ResourcesCompat
import com.example.ui.R

fun View.drawable(@DrawableRes drawableRes: Int): Drawable? = AppCompatResources.getDrawable(context, drawableRes)

fun View.dimension(@DimenRes dimenRes: Int) = resources.getDimension(dimenRes)

fun View.string(@StringRes stringRes: Int) = resources.getString(stringRes)

fun View.color(@ColorRes color: Int) = AppCompatResources.getColorStateList(context, color)

fun View.font(@FontRes font: Int) = ResourcesCompat.getFont(context, font)

val View.primaryColor
    get() = color(R.color.colorPrimary)

val View.primaryDarkColor
    get() = color(R.color.colorPrimaryDark)

val View.accentColor
    get() = color(R.color.colorAccent)

fun ViewGroup.inflate(@LayoutRes resourceId: Int) = View.inflate(context, resourceId, this)

fun View.getIconResourceValue(a: TypedArray, styleableId: Int) = TypedValue().also {
    a.getValue(styleableId, it)
}.resourceId

fun View.hide() = run {
    visibility = View.GONE
}

fun View.show() = run {
    visibility = View.VISIBLE
}

fun View.invisible() = run {
    visibility = View.INVISIBLE
}

fun View.visibility(show : Boolean, invisible : Boolean = false) = run {
    if (show) show() else if (invisible) invisible() else hide()
}