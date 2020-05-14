package com.example.reciters

import android.view.View
import com.example.data.reciters.ReciterWrapper
import kotlinx.android.synthetic.main.translators_layout.view.*

class RecitersListView(private val recitersAdapter: RecitersAdapter = RecitersAdapter()) {

    private var translatorsView: View? = null
    private var recitersList: ArrayList<ReciterWrapper> = arrayListOf()
    lateinit var itemClickListener: (ReciterWrapper) -> Unit

    fun init(view: View?, itemsList: List<ReciterWrapper>) = kotlin.run {
        translatorsView = view
        recitersList = itemsList as ArrayList<ReciterWrapper>
        initialise()
    }

    private fun initialise() {
        translatorsView?.apply {
            translatorsRecyclerView.adapter = recitersAdapter.apply {
                itemClickListener = this@RecitersListView.itemClickListener
                listItems = recitersList
            }
        }
    }
}