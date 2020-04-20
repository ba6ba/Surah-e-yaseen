package com.example.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.recitation.RecitationFragment
import com.example.tilawat.TilawatFragment
import com.example.ui.bottomTab.FloatingBottomNavigation.Companion.NO_OF_TABS

class HomeAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = NO_OF_TABS

    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> TilawatFragment.newInstance()
            1 -> RecitationFragment.newInstance()
            else -> TilawatFragment.newInstance()
        }

}