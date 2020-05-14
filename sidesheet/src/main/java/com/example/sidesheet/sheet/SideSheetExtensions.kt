package com.example.sidesheet.sheet

import android.view.View
import androidx.appcompat.widget.AppCompatImageButton
import com.example.sidesheet.R
import com.example.ui.extensions.animateByPushingLeftIn
import com.example.ui.extensions.animateByPushingLeftOut
import com.example.ui.extensions.animateByPushingRightIn
import com.example.ui.extensions.animateByPushingRightOut

fun SideSheet.provideAppropriateAnimation(view: View, it: SideSheetStates, animationCompleted : ((Boolean) -> Unit) ? = null) {
    view.apply {
        when(it) {
            SideSheetStates.COLLAPSE -> {
                if (OpenFrom.END == openFrom) {
                    animateByPushingRightOut(context, animationCompleted)
                }
                else {
                    animateByPushingLeftOut(context, animationCompleted)
                }
            }

            SideSheetStates.EXPAND -> {
                if (OpenFrom.END == openFrom) {
                    animateByPushingLeftIn(context, animationCompleted)
                }
                else {
                    animateByPushingRightIn(context, animationCompleted)
                }
            }
        }
    }
}

fun SideSheet.provideAppropriateIcon(view: AppCompatImageButton) = run {
    if (OpenFrom.START == openFrom) {
        if (view.id == R.id.expand) R.drawable.side_sheet_collapse_icon else if (view.id == R.id.collapse) R.drawable.side_sheet_expand_icon else 0
    }
    else {
        if (view.id == R.id.expand) R.drawable.side_sheet_expand_icon else if (view.id == R.id.collapse) R.drawable.side_sheet_collapse_icon else 0
    }
}