package com.example.ui.horizontalcell

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.extensions.isTrue
import com.example.ui.R
import com.example.ui.extensions.*
import com.example.ui.horizontalcell.CustomHorizontalCellState.DEFAULT
import com.example.ui.horizontalcell.CustomHorizontalCellState.HIGHLIGHTED
import kotlinx.android.synthetic.main.custom_horizontal_cell.view.*

class CustomHorizontalCell @JvmOverloads constructor(
    context: Context,
    private val attributeSet: AttributeSet? = null,
    private val defStyleRes: Int = 0
) : ConstraintLayout(context, attributeSet, defStyleRes) {

    private val defaultColorStateList = colorStateList(R.color.colorGrey)
    private val defaultBackgroundSelector = drawable(R.drawable.custom_horizontal_cell_background)!!
    var onClickListener: ((Any, CustomHorizontalCellState) -> Unit)? = null
    var state: CustomHorizontalCellState = DEFAULT
        set(value) {
            field = value
            onClickListener?.invoke(text, value)
            updateTextAppearance()
        }

    var backgroundSelector: Drawable = defaultBackgroundSelector
        set(value) {
            field = value
            cell.background = value
        }

    var text: String = ""
        set(value) {
            field = value
            textView.text = value
        }

    var iconRes: Int = -1
        set(value) {
            value.isValidResourceId().isTrue {
                field = value
                icon.apply {
                    show()
                    setImageResource(value)
                }
            }
        }

    var iconColor: ColorStateList = defaultColorStateList
        set(value) {
            field = value
            icon.backgroundTintList = value
        }

    init {
        inflate(R.layout.custom_horizontal_cell)
        getAttributes(attributeSet)
        setListener()
    }

    private fun setListener() {
        cell.setOnClickListener {
            updateState()
            state = if (isSelected) HIGHLIGHTED else DEFAULT
        }
    }

    fun updateState(update : Boolean = !isSelected) {
        isSelected = update
        updateTextAppearance()
    }

    private fun updateTextAppearance() = textView.setTextColor(cell.getColorBasedOnLuminance)

    private fun getAttributes(attributeSet: AttributeSet?) {
        getStyleAttributes(R.styleable.CustomHorizontalCell, attributeSet).apply {
            try {
                backgroundSelector = getDrawable(R.styleable.CustomHorizontalCell_backgroundSelector) ?: defaultBackgroundSelector
                text = getString(R.styleable.CustomHorizontalCell_text) ?: ""
                iconRes = getResourceId(R.styleable.CustomHorizontalCell_icon, -1)
                iconColor = getColorStateList(R.styleable.CustomHorizontalCell_colorIcon) ?: defaultColorStateList
            } finally {
                recycle()
            }
        }
    }
}