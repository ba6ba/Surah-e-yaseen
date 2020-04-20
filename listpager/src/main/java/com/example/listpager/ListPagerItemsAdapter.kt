package com.example.listpager

import android.view.View
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

class ListPagerItemsAdapter<T>(
    @LayoutRes private val layoutResId: Int,
    private var viewHolder: (View) -> RecyclerView.ViewHolder,
    private val viewHolderCallBack: (RecyclerView.ViewHolder, MutableList<T>, Int) -> Unit
) : BasePagerAdapter<T>(layoutResId, viewHolder, viewHolderCallBack) {

    override var itemsList: MutableList<*> = mutableListOf<T>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

}