package com.example.ui.sideDrawer

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.lifecycle.MutableLiveData
import com.example.ui.R
import com.example.ui.extensions.*
import kotlinx.android.synthetic.main.custom_side_sheet.view.*

class SideSheet @JvmOverloads constructor(context: Context, private val attributeSet: AttributeSet? = null, private val defStyleRes: Int = 0) :
    RelativeLayout(context, attributeSet, defStyleRes) {

    private val sideSheetManager: SideSheetManager = SideSheetManager(this)
    val stateObserver: MutableLiveData<SideSheetStates> = MutableLiveData()
    var onStateChangeListener : ((SideSheetStates) -> Unit) ? = null

    init {
        inflate(R.layout.custom_side_sheet)
        observerStateListener()
        onClickListeners()
    }

    private fun observerStateListener() {
        stateObserver.observeForever {
            when(it) {
                SideSheetStates.COLLAPSE -> {
                    collapseLayout.hide().animateByPushingRightOut(context)
                    expand.show().animateByFadingIn(context)
                    sheet.setBackgroundColor(color(R.color.colorTransparent))
                }

                SideSheetStates.EXPAND -> {
                    expand.hide().animateByFadingOut(context)
                    collapseLayout.show().animateByPushingLeftIn(context)
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