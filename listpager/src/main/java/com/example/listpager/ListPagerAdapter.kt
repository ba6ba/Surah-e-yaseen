package com.example.listpager

import android.view.View
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

class ListPagerAdapter<T>(
    @LayoutRes private val layoutResId: Int,
    private val viewHolder: (View) -> RecyclerView.ViewHolder,
    private val viewHolderCallback: (RecyclerView.ViewHolder, List<T>, Int) -> Unit
) : BasePagerAdapter<T>(layoutResId, viewHolder, viewHolderCallback) {

    override var itemsList: List<*> = mutableListOf<T>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
}