package com.example.ui.bottomTab

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.example.extensions.isTrue
import com.example.ui.R
import com.example.ui.extensions.*
import kotlinx.android.synthetic.main.floating_bottom_navigation.view.*

class FloatingBottomNavigation @JvmOverloads constructor(
    context: Context, private val attributeSet: AttributeSet? = null, private val defStyleRes:
    Int = 0
) :
    FrameLayout(context, attributeSet, defStyleRes) {

    fun getTabLayout() = tabLayout

    companion object {
        private const val TAB_1 = 0
        private const val TAB_2 = 1
        const val NO_OF_TABS = 2
        const val RECITATION_TAG = "RECITATION"
        const val TILAWAT_TAG = "TILAWAT"
    }

    init {
        View.inflate(context, R.layout.floating_bottom_navigation, this)
    }

    fun initTabs() {
        tabLayout.apply {
            getTabAt(TAB_1)?.customView = tilawatTab()
            getTabAt(TAB_2)?.customView = recitationTab()
        }
        addDivider()
        setOnPageChangeListener(TAB_1)
    }

    private fun addDivider() {
        (tabLayout.getChildAt(TAB_1) as LinearLayout).apply {
            showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
            dividerDrawable = drawable(R.drawable.tabs_divider)
        }
    }

    fun setOnPageChangeListener(position: Int) {
        for (i in TAB_1..NO_OF_TABS) {
            (position == i).isTrue { getTab(position)?.selectTab() } ?: getTab(i)?.deSelectTab()
        }
    }

    private fun getTab(position: Int) = (tabLayout.getTabAt(position)?.customView as? FloatingTabItem)
}