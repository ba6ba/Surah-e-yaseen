package com.example.translators

import android.view.View
import kotlinx.android.synthetic.main.translators_layout.view.*

class TranslatorsList {

    private var translatorsAdapter : TranslatorsAdapter = TranslatorsAdapter()
    private var translatorsView : View? = null

    fun init(view: View?) = kotlin.run {
        translatorsView = view
        initialise()
    }

    private fun initialise() {
        translatorsView?.apply {
            translatorsRecyclerView.adapter = translatorsAdapter.apply {
                onClickListener = {
                    // hit Api to get tilawat of this translator
                }
            }
        }
    }
}