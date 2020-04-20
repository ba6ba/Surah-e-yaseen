package com.example.ui.extensions

import com.example.ui.R
import com.example.ui.bottomTab.FloatingBottomNavigation
import com.example.ui.bottomTab.FloatingTabItem

fun FloatingBottomNavigation.tilawatTab() = FloatingTabItem.newItem(
    context, FloatingBottomNavigation.TILAWAT_TAG, string(R.string.main_bottom_navigation_tialwat_title), R.drawable.ic_tilawat
)

fun FloatingBottomNavigation.recitationTab() = FloatingTabItem.newItem(
    context, FloatingBottomNavigation.RECITATION_TAG, string(R.string.main_bottom_navigation_recitation_title), R.drawable.ic_recitation
)