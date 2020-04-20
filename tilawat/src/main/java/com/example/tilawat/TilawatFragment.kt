package com.example.tilawat

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.core.BaseFragment
import com.example.core.FlowData
import com.example.sidesheet.sheet.SideSheetStates
import com.example.translators.TranslatorsAdapter
import com.example.translators.TranslatorsListView
import com.example.translators.TranslatorsProvider
import kotlinx.android.synthetic.main.fragment_tilawat.*
import org.koin.android.ext.android.inject

class TilawatFragment : BaseFragment(R.layout.fragment_tilawat) {

    private var translatorsAdapter : TranslatorsAdapter = TranslatorsAdapter()
    private val translatorsProvider : TranslatorsProvider by inject()
    private var translatorsListView : TranslatorsListView = TranslatorsListView(translatorsAdapter, translatorsProvider)

    companion object {
        fun newInstance() = TilawatFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        translatorsListView.init(viewLifecycleOwner, sideSheet.childView)
        sideSheet.apply {
            onStateChangeListener = {
                FlowData.viewStateChangeLiveData.postValue(SideSheetStates.EXPAND == it)
            }
        }
        translatorsProvider.onTranslationSelection.observe(viewLifecycleOwner, Observer {
            sideSheet.collapseTrigger()
        })
    }
}