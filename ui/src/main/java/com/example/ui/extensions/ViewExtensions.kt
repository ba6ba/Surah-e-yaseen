package com.example.ui.extensions

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import androidx.annotation.*
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.ColorUtils
import com.example.extensions.doAnimation
import com.example.ui.R

fun View.drawable(@DrawableRes drawableRes: Int): Drawable? = AppCompatResources.getDrawable(context, drawableRes)

fun View.dimension(@DimenRes dimenRes: Int) = resources.getDimension(dimenRes)

fun View.string(@StringRes stringRes: Int) = resources.getString(stringRes)

fun View.colorStateList(@ColorRes color: Int) = AppCompatResources.getColorStateList(context, color)

fun View.color(@ColorRes color: Int) = resources.getColor(color)

fun View.font(@FontRes font: Int) = ResourcesCompat.getFont(context, font)

val View.primaryColor
    get() = colorStateList(R.color.colorPrimary)

val View.primaryDarkColor
    get() = colorStateList(R.color.colorPrimaryDark)

val View.accentColor
    get() = colorStateList(R.color.colorAccent)

fun ViewGroup.inflate(@LayoutRes resourceId: Int) = View.inflate(context, resourceId, this)

fun View.getIconResourceValue(a: TypedArray, styleableId: Int) = TypedValue().also {
    a.getValue(styleableId, it)
}.resourceId

fun View.hide() = run {
    visibility = View.GONE
    this
}

fun View.show() = run {
    visibility = View.VISIBLE
    this
}

fun View.invisible() = run {
    visibility = View.INVISIBLE
    this
}

fun View.visibility(show : Boolean, invisible : Boolean = false) = run {
    if (show) show() else if (invisible) invisible() else hide()
}

fun View.animateByPushingLeftIn(context: Context) = run {
    doAnimation(context, R.anim.push_left_in)
    this
}

fun View.animateByPushingRightIn(context: Context) = run {
    doAnimation(context, R.anim.push_right_in)
    this
}

fun View.animateByPushingLeftOut(context: Context) = kotlin.run {
    doAnimation(context, R.anim.push_left_out)
    this
}

fun View.animateByPushingRightOut(context: Context) = kotlin.run {
    doAnimation(context, R.anim.push_right_out)
    this
}

fun View.animateByFadingIn(context: Context) = kotlin.run {
    doAnimation(context, R.anim.fade_in)
    this
}

fun View.animateByFadingOut(context: Context) = kotlin.run {
    doAnimation(context, R.anim.fade_out)
    this
}

fun <T : AttributeSet?> View.getStyleAttributes(styleableId: IntArray, t : T) =
    context.theme.obtainStyledAttributes(t, styleableId, 0, 0)

fun View.getDp(value : Int) = value.times(4)

val View.constraintWrapLayoutParams
    get() = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT)

val View.constraintMatchParentLayoutParams
    get() = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT)

val View.isDarkColor
    get() = ColorUtils.calculateLuminance((background as? ColorDrawable)?.color ?: color(R.color.colorAccent)) < 0.5

val View.getColorBasedOnLuminance
    get() = if (isDarkColor) color(R.color.colorBlack) else R.color.colorWhite