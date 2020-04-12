package com.example.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.example.core.FlowData
import com.example.ui.sideDrawer.SideSheetStates
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_home.*

class MainFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        bottomNavigation.apply {
            TabLayoutMediator(getTabLayout(),
                viewPager.apply {
                    adapter = HomeAdapter(this@MainFragment)
                    registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
                        override fun onPageSelected(position: Int) {
                            setOnPageChangeListener(position)
                        }
                    })
                }) { tab, position -> }
                .attach()
            initTabs()
        }

        FlowData.viewStateChangeLiveData.observe(viewLifecycleOwner, Observer {
            if (SideSheetStates.EXPAND == it) {
                viewPager.bringToFront()
            }
            else {
                bottomNavigation.bringToFront()
            }
        })
    }
}