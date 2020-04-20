package com.example.listpager

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_pager_item_layout.view.*

class ListPagerViewPagerHolder<T>(
    view: View,
    private val itemsAdapter: ListPagerItemsAdapter<T>
) : RecyclerView.ViewHolder(view) {

    fun onBind(pagerList: PagerList<T>) {
        itemView.apply {
            itemsRecyclerView.apply {
                adapter = itemsAdapter.apply {
                    itemsList = pagerList.item
                }
            }
        }
    }
}