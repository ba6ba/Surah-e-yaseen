package com.example.listpager

import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.example.ui.extensions.getView

abstract class BasePagerAdapter<T>(
    @LayoutRes private val layoutResId: Int,
    private val viewHolder: (View) -> RecyclerView.ViewHolder,
    private val viewHolderCallback: (RecyclerView.ViewHolder, List<T>, Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    abstract var itemsList: List<*>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = viewHolder(getView(layoutResId, parent))

    override fun getItemCount(): Int = itemsList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = viewHolderCallback(holder, itemsList as List<T>, position)

}