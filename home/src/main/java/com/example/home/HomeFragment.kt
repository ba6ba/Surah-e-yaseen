package com.example.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.example.core.BaseFragment
import com.example.core.FlowData
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        //TODO 17-april-2020 -- lock swipe behavior
        bottomNavigation.apply {
            TabLayoutMediator(getTabLayout(),
                viewPager.apply {
                    adapter = HomeAdapter(this@HomeFragment)
                    registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                        override fun onPageSelected(position: Int) {
                            setOnPageChangeListener(position)
                        }
                    })
                }) { tab, position -> }
                .attach()
            initTabs()
        }

        FlowData.viewStateChangeLiveData.observe(viewLifecycleOwner, Observer {
            if (it) {
                viewPager.bringToFront()
            } else {
                bottomNavigation.bringToFront()
            }
        })
    }
}