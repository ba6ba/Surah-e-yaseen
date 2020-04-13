package com.example.ui.extensions

import android.view.View
import androidx.appcompat.widget.AppCompatImageButton
import com.example.ui.R
import com.example.ui.sideDrawer.OpenFrom
import com.example.ui.sideDrawer.SideSheet
import com.example.ui.sideDrawer.SideSheetStates

fun SideSheet.provideAppropriateAnimation(view: View, it: SideSheetStates) {
    view.apply {
        when(it) {
            SideSheetStates.COLLAPSE -> {
                if (OpenFrom.END == openFrom) {
                    animateByPushingRightOut(context)
                }
                else {
                    animateByPushingLeftOut(context)
                }
            }

            SideSheetStates.EXPAND -> {
                if (OpenFrom.END == openFrom) {
                    animateByPushingLeftIn(context)
                }
                else {
                    animateByPushingRightIn(context)
                }
            }
        }
    }
}

fun SideSheet.provideAppropriateIcon(view: AppCompatImageButton) = run {
    if (OpenFrom.START == openFrom) {
        if (view.id == R.id.expand) R.drawable.nav_icon_collapse else if (view.id == R.id.collapse) R.drawable.nav_icon_expand else 0
    }
    else {
        if (view.id == R.id.expand) R.drawable.nav_icon_expand else if (view.id == R.id.collapse) R.drawable.nav_icon_collapse else 0
    }
}