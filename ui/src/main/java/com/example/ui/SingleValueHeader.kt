package com.example.ui

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ui.extensions.inflateAndGetStyleAttributes
import kotlinx.android.synthetic.main.single_value_header_layout.view.*

class SingleValueHeader @JvmOverloads constructor(context: Context, private val attributeSet: AttributeSet? = null,
    private val defStyleRes : Int = 0) : ConstraintLayout(context, attributeSet, defStyleRes) {

    var heading : String = ""
    set(value) {
        field = value
        headingView.textKey = value
    }

    var value : String = ""
        set(value) {
            field = value
            valueView.textKey = value
        }

    init {
        inflateAndGetStyleAttributes(R.layout.single_value_header_layout, R.styleable.SingleValueHeader, attributeSet) {
            heading = getString(R.styleable.SingleValueHeader_heading) ?: ""
            value = getString(R.styleable.SingleValueHeader_value) ?: ""
        }
    }
}