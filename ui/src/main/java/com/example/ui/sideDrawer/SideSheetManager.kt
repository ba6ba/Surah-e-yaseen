package com.example.ui.sideDrawer

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
}