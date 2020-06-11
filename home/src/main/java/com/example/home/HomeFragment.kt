package com.example.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.example.core.BaseFragment
import com.example.core.BaseViewModel
import com.example.shared.FlowData
import com.example.extensions.observeOnce
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private val homeViewModel : HomeViewModel by viewModel()

    override fun getViewModel(): BaseViewModel? {
        return homeViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        bottomNavigation.apply {
            TabLayoutMediator(getTabLayout(),
                viewPager.apply {
                    isUserInputEnabled = false
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeOnce(homeViewModel.fetchTilawatData()) {
            // handles in viewmodel and provider
        }
    }
}