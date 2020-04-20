package com.example.sidesheet.sheet

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.example.extensions.getWindowWidth
import com.example.extensions.isTrue

class SideSheetManager(private val sideSheet: SideSheet) {

    var state: SideSheetStates = SideSheetStates.COLLAPSE
        set(value) {
            field = value
            sideSheet.stateObserver.postValue(field)
        }

    fun updateState(sheetState: SideSheetStates) =
        (state != sheetState).isTrue {
            state = sheetState
        }

    fun getSheetWidth(context: Context, mode: Mode, currentWidth: Int, collapseButtonSize : Int) = kotlin.run {
        when(mode) {
            Mode.FULL -> (context as AppCompatActivity).getWindowWidth - collapseButtonSize.div(2)
            Mode.HALF -> (context as AppCompatActivity).getWindowWidth.div(2)
            else -> currentWidth
        }
    }
}