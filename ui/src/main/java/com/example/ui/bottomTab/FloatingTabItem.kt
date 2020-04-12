package com.example.ui.bottomTab

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ui.R
import com.example.ui.extensions.*
import kotlinx.android.synthetic.main.floating_tab_item.view.*

class FloatingTabItem @JvmOverloads constructor(
    context: Context,
    private val attributeSet: AttributeSet? = null, private val defStyleRes: Int = 0
) :
    ConstraintLayout(context, attributeSet, defStyleRes) {

    companion object {
        fun newItem(
            context: Context,
            tag : String,
            title: String,
            icon: Int
        ) = FloatingTabItem(context).apply {
            iconRes = icon
            text = title
            setTag(tag)
        }
    }

    private val defaultColorStateList = primaryColor

    var textTint = defaultColorStateList
        set(value) {
            field = value
            title.setTextColor(field)
        }

    var indicatorTint = defaultColorStateList
        set(value) {
            field = value
            indicator.backgroundTintList = field
        }

    var iconTint = defaultColorStateList
        set(value) {
            field = value
            icon.imageTintList = field
        }

    var iconRes: Int = -1
        set(value) {
            if (value.isValidResourceId()) {
                field = value
                icon.setImageResource(field)
            }
        }

    var indicatorRes: Int = R.drawable.floating_tab_item_indicator
        set(value) {
            if (value.isValidResourceId()) {
                field = value
                icon.setImageResource(field)
            }
        }

    var text: String? = ""
        set(value) {
            field = value
            title.text = text
        }

    init {
        View.inflate(context, R.layout.floating_tab_item, this)
        getAttributes(attributeSet)
    }

    private fun getAttributes(attributeSet: AttributeSet?) {
        context.theme.obtainStyledAttributes(
            attributeSet, R.styleable.FloatingTabItem, 0,
            0
        ).apply {
            indicatorTint = getColorStateList(R.styleable.FloatingTabItem_indicatorTint)
            iconTint = getColorStateList(R.styleable.FloatingTabItem_iconBackground)
            iconRes = getIconResourceValue(this, R.styleable.FloatingTabItem_icon)
            indicatorRes = getIconResourceValue(this, R.styleable.FloatingTabItem_indicator)
            text = getString(R.styleable.FloatingTabItem_title)
        }
    }



    fun selectTab(selectColor: ColorStateList = color(R.color.colorAccent)) = run {
        iconTint = selectColor
        textTint = selectColor
        indicator.show()
    }

    fun deSelectTab(deSelectColor: ColorStateList = color(R.color.colorAccent_50)) = run {
        iconTint = deSelectColor
        textTint = deSelectColor
        indicator.hide()
    }
}
