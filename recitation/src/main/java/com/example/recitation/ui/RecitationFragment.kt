package com.example.recitation.ui

import android.os.Bundle
import android.view.View
import com.example.core.BaseFragment
import com.example.core.BaseViewModel
import com.example.extensions.observeOnceOnLifecycle
import com.example.recitation.R
import com.example.recitation.RecitationViewModel
import kotlinx.android.synthetic.main.fragment_recitation.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecitationFragment : BaseFragment(R.layout.fragment_recitation) {

    private val recitationViewModel : RecitationViewModel by viewModel()
    override fun getViewModel(): BaseViewModel? {
        return recitationViewModel
    }

    companion object {
        fun newInstance() = RecitationFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setObservers()
    }

    private fun setObservers() {
        recitationViewModel.apply {
            observeOnceOnLifecycle(canFetchVerses) { nothing ->
                requestForVersesData()
            }
        }
    }

    private fun requestForVersesData(pageNo : Int = 0) {
        observeOnceOnLifecycle(recitationViewModel.fetchVerses(recitationsListPager, pageNo)) { chapter ->
            recitationsListPager.populateData(chapter?.verses.orEmpty())
        }
    }

    private fun initViews() {
        recitationsListPager.apply {
            onItemClickListener = { verse ->
                verse
            }

            listPagerSwitcher.setupWithListPager(this)

            onPageChangeCallbackListener = { pageNo ->
                requestForVersesData(pageNo = pageNo)
            }
        }
    }
}