package com.example.translators

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.translators_layout.view.*

class TranslatorsListView(private val translatorsAdapter: TranslatorsAdapter,
    private val translatorsProvider : TranslatorsProvider) {

    private var translatorsView : View? = null
    lateinit var lifecycleOwner: LifecycleOwner

    fun init(viewLifecycleOwner: LifecycleOwner, view: View?) = kotlin.run {
        lifecycleOwner = viewLifecycleOwner
        translatorsView = view
        initialise()
    }

    private fun initialise() {
        translatorsView?.apply {
            translatorsRecyclerView.adapter = translatorsAdapter.apply {
                itemClickListener = {
                    translatorsProvider.onTranslationSelection.value = it
                }
                translatorsProvider.translatorsList().observe(lifecycleOwner, Observer {
                    listItems = it
                })
            }
        }
    }
}