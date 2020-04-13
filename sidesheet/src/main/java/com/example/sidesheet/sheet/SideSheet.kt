package com.example.sidesheet.sheet

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import androidx.annotation.LayoutRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.lifecycle.MutableLiveData
import com.example.extensions.isFalse
import com.example.extensions.isTrue
import com.example.sidesheet.R
import com.example.ui.extensions.*
import kotlinx.android.synthetic.main.custom_side_sheet.view.*

class SideSheet @JvmOverloads constructor(context: Context, private val attributeSet: AttributeSet? = null, private val defStyleRes: Int = 0) :
    RelativeLayout(context, attributeSet, defStyleRes) {

    private val sideSheetManager: SideSheetManager = SideSheetManager(this)
    val stateObserver: MutableLiveData<SideSheetStates> = MutableLiveData()
    var onStateChangeListener: ((SideSheetStates) -> Unit)? = null
    var childView: View? = null
    var sheetWidth: Int = 0
    var sheetHeight: Int = ConstraintLayout.LayoutParams.MATCH_PARENT
    lateinit var childLayoutInflateCompleteListener : (View?) -> Unit
    var openFrom: OpenFrom = OpenFrom.END
    @LayoutRes
    var childLayout: Int = -1
        set(value) {
            value.isValidResourceId().isTrue {
                field = value
                inflateChildViewAndApplyConstraints(value)
            }
        }

    var mode: Mode = Mode.CUSTOM
        set(value) {
            field = value
            sheetWidth = sideSheetManager.getSheetWidth(context, field, sheetWidth, collapse.drawable.intrinsicWidth)
        }

    var expandIconRes: Int = -1
        set(value) {
            value.isValidResourceId().isTrue {
                field = value
                expand.setImageResource(field)
            }
        }

    var collapseIconRes: Int = -1
        set(value) {
            value.isValidResourceId().isTrue {
                field = value
                collapse.setImageResource(field)
            }
        }

    var sheetBackground: Int = -1
        set(value) {
            value.isValidResourceId().isTrue {
                field = value
                applyBackgroundToSheet(field)
            }
        }

    init {
        inflate(R.layout.custom_side_sheet)
        getAttributes(attributeSet)
        observerStateListener()
        onClickListeners()
    }

    private fun inflateChildViewAndApplyConstraints(value: Int) {
        childStub.apply {
            layoutResource = value
            layoutParams = applySheetConstraints()
        }
        childView = childStub.inflate()
        childLayoutInflateCompleteListener(childView)
        updateButtons()
    }

    private fun updateButtons() {
        updateButtonsIcons()
        updateButtonsConstraints()
    }

    private fun updateButtonsIcons() {
        collapseIconRes.isValidResourceId().isFalse {
            collapseIconRes = provideAppropriateIcon(collapse)
        }

        expandIconRes.isValidResourceId().isFalse {
            expandIconRes = provideAppropriateIcon(expand)
        }
    }

    private fun updateButtonsConstraints() {
        updateExpandIconConstraints()
        updateCollapseIconConstraints()
    }

    private fun updateExpandIconConstraints() {
        expand.apply {
            layoutParams = constraintWrapLayoutParams.apply {
                if (OpenFrom.START == openFrom) {
                    applyParentTopLeftConstraints().applyTopLeftMargins(getDp(16), getDp(16))
                } else {
                    applyParentTopRightConstraints().applyTopRightMargins(getDp(16), getDp(16))
                }
            }
        }
    }

    fun applySheetConstraints(
        top: Int = ConstraintSet.PARENT_ID, left: Int = ConstraintSet.PARENT_ID,
        bottom: Int = ConstraintSet.PARENT_ID, right: Int = ConstraintSet.PARENT_ID
    ) =
        ConstraintLayout.LayoutParams(sheetWidth, sheetHeight).apply {
            applyParentConstraints(top, left, bottom, right)
            horizontalBias = if (OpenFrom.END == openFrom) {
                1F
            } else {
                0F
            }
        }

    private fun updateCollapseIconConstraints() {
        collapse.apply {
            layoutParams = constraintWrapLayoutParams.apply {
                applyParentVerticalConstraints()
                if (Mode.HALF != mode) {
                    if (OpenFrom.START == openFrom) {
                        startToStart = ConstraintSet.PARENT_ID
                        marginStart = (childView?.layoutParams?.width ?: 0).minus(drawable.intrinsicWidth.div(2))
                    } else if (OpenFrom.END == openFrom) {
                        endToEnd = ConstraintSet.PARENT_ID
                        marginEnd = (childView?.layoutParams?.width ?: 0).minus(drawable.intrinsicWidth.div(2))
                    }
                } else {
                    applyParentHorizontalConstraints()
                }
            }
        }
    }


    private fun applyBackgroundToSheet(field: Int) {
        drawable(field)?.apply {
            childView?.background = this
        } ?: color(field).apply {
            childView?.setBackgroundColor(this)
        }
    }

    private var sheetElevation: Float = -1F
        set(value) {
            value.isValidResourceId().isTrue {
                field = value
                setElevationToViews(value)
            }
        }

    private fun setElevationToViews(value: Float) {
        childView?.elevation = value
        collapse?.elevation = value.plus(getDp(2))
    }

    private fun getAttributes(attributeSet: AttributeSet?) {
        getStyleAttributes(R.styleable.SideSheet, attributeSet).apply {
            try {
                collapseIconRes = getResourceId(R.styleable.SideSheet_collapseIcon, -1)
                sheetWidth = getDimension(R.styleable.SideSheet_customWidth, dimension(R.dimen.side_sheet_width)).toInt()
                sheetHeight = getDimension(R.styleable.SideSheet_customHeight, -1F).toInt()
                mode = Mode.get(getInt(R.styleable.SideSheet_mode, 2))
                openFrom = OpenFrom.get(getInt(R.styleable.SideSheet_openFrom, 1))
                childLayout = getResourceId(R.styleable.SideSheet_childLayout, -1)
                sheetBackground = getResourceId(R.styleable.SideSheet_sheetBackground, -1)
                expandIconRes = getResourceId(R.styleable.SideSheet_expandIcon, -1)
                sheetElevation = getDimension(R.styleable.SideSheet_sheetElevation, -1F)
            } finally {
                recycle()
            }
        }
    }

    private fun observerStateListener() {
        stateObserver.observeForever {
            when (it) {
                SideSheetStates.COLLAPSE -> {
                    provideAppropriateAnimation(collapseLayout.hide(), it)
                    expand.show().animateByFadingIn(context)
                    sheet.setBackgroundColor(color(R.color.colorTransparent))
                }

                SideSheetStates.EXPAND -> {
                    expand.hide().animateByFadingOut(context)
                    provideAppropriateAnimation(collapseLayout.show(), it)
                    sheet.setBackgroundColor(color(R.color.colorLightTransparent))
                }
            }
            onStateChangeListener?.invoke(it)
        }
    }

    private fun onClickListeners() {
        expand.setOnClickListener {
            sideSheetManager.updateState(SideSheetStates.EXPAND)
        }

        collapse.setOnClickListener {
            collapseSheet()
        }

        sheet.setOnClickListener {
            collapseSheet()
        }
    }

    private fun collapseSheet() {
        sideSheetManager.updateState(SideSheetStates.COLLAPSE)
    }
}