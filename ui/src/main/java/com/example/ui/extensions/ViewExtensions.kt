package com.example.ui.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import androidx.annotation.*
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.ColorUtils
import com.example.extensions.doAnimation
import com.example.extensions.isVersionLowerThan
import com.example.ui.R

fun View.drawable(@DrawableRes drawableRes: Int): Drawable? = AppCompatResources.getDrawable(context, drawableRes)

fun View.dimension(@DimenRes dimenRes: Int) = resources.getDimension(dimenRes)

fun View.string(@StringRes stringRes: Int) = resources.getString(stringRes)

fun View.colorStateList(@ColorRes color: Int): ColorStateList = AppCompatResources.getColorStateList(context, color)

fun View.color(@ColorRes color: Int) = resources.getColor(color)

fun View.font(@FontRes font: Int) = ResourcesCompat.getFont(context, font)

val View.primaryColor
    get() = colorStateList(R.color.colorPrimary)

val View.primaryDarkColor
    get() = colorStateList(R.color.colorPrimaryDark)

val View.accentColor
    get() = colorStateList(R.color.colorAccent)

fun ViewGroup.inflate(@LayoutRes resourceId: Int): View = View.inflate(context, resourceId, this)

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

fun View.visibility(show: Boolean, invisible: Boolean = false) = run {
    if (show) show() else if (invisible) invisible() else hide()
}

fun View.animateByPushingLeftIn(context: Context, animationCompleted: ((Boolean) -> Unit)? = null) = run {
    doAnimation(context, R.anim.push_left_in, animationCompleted)
    this
}

fun View.animateByPushingRightIn(context: Context, animationCompleted: ((Boolean) -> Unit)? = null) = run {
    doAnimation(context, R.anim.push_right_in, animationCompleted)
    this
}

fun View.animateByPushingLeftOut(context: Context, animationCompleted: ((Boolean) -> Unit)? = null) = kotlin.run {
    doAnimation(context, R.anim.push_left_out, animationCompleted)
    this
}

fun View.animateByPushingRightOut(context: Context, animationCompleted: ((Boolean) -> Unit)? = null) = kotlin.run {
    doAnimation(context, R.anim.push_right_out, animationCompleted)
    this
}

fun View.animateByFadingIn(context: Context, animationCompleted: ((Boolean) -> Unit)? = null) = kotlin.run {
    doAnimation(context, R.anim.fade_in, animationCompleted)
    this
}

fun View.animateByFadingOut(context: Context, animationCompleted: ((Boolean) -> Unit)? = null) = kotlin.run {
    doAnimation(context, R.anim.fade_out, animationCompleted)
    this
}

fun <T : AttributeSet?> View.getStyleAttributes(styleableId: IntArray, t: T): TypedArray =
    context.theme.obtainStyledAttributes(t, styleableId, 0, 0)

fun <T : AttributeSet?> ViewGroup.inflateAndGetStyleAttributes(
    @LayoutRes resourceId: Int,
    styleableId: IntArray, t: T, typedArray: TypedArray.() -> Unit
): View =
    inflate(resourceId).apply {
        typedArray(getStyleAttributes(styleableId, t))
    }

fun View.getDp(value: Int) = value.times(4)

val View.constraintWrapLayoutParams
    get() = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT)

val View.constraintMatchParentLayoutParams
    get() = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT)

val View.constraintWidthMatchConstraint
    get() = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, ConstraintLayout.LayoutParams.WRAP_CONTENT)

val View.isDarkColor
    @SuppressLint("NewApi")
    get() = ColorUtils.calculateLuminance(
        when (val bg = background) {
            is ColorDrawable -> bg.color
            is RippleDrawable -> bg.getCurrentDrawableColor()
            is GradientDrawable -> if (isVersionLowerThan(23)) R.color.colorAccent else bg.color?.defaultColor
            else -> null
        } ?: color(R.color.colorWhite)
    ) < 0.5

val View.getColorBasedOnLuminance
    get() = color(if (isDarkColor) R.color.colorWhite else R.color.colorBlack)