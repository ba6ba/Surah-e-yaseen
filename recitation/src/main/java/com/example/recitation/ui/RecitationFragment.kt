package com.example.recitation.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.core.BaseFragment
import com.example.core.BaseViewModel
import com.example.extensions.observeOnce
import com.example.extensions.showToast
import com.example.recitation.R
import com.example.recitation.RecitationItemsProvider
import com.example.recitation.RecitationViewModel
import kotlinx.android.synthetic.main.fragment_recitation.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecitationFragment : BaseFragment(R.layout.fragment_recitation) {

    private val recitationItemsProvider : RecitationItemsProvider by inject()
    private val recitationViewModel : RecitationViewModel by viewModel()
    override fun <M : BaseViewModel> getViewModel(): BaseViewModel? = recitationViewModel

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recitationViewModel.chapter.observeOnce(viewLifecycleOwner) {
            showToast(it.arabicName.toString())
        }
    }
}