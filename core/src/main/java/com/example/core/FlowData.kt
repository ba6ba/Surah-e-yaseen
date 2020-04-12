package com.example.core

import androidx.lifecycle.MutableLiveData
import com.example.ui.sideDrawer.SideSheetStates

object FlowData {

    val viewStateChangeLiveData : MutableLiveData<SideSheetStates> = MutableLiveData()
}