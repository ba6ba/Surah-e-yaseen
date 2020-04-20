package com.example.recitation

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.core.BaseFragment
import kotlinx.android.synthetic.main.fragment_recitation.*
import org.koin.android.ext.android.inject

class RecitationFragment : BaseFragment(R.layout.fragment_recitation) {

    private val recitationItemsProvider : RecitationItemsProvider by inject()

    companion object {
        fun newInstance() = RecitationFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recitationsListPager.apply {
            onItemClickListener = { verse ->
                verse
            }

            recitationItemsProvider.recitationsLiveData.observe(viewLifecycleOwner, Observer {
                populateData(it)
            })

            listPagerSwitcher.setupWithListPager(this)
        }
    }
}