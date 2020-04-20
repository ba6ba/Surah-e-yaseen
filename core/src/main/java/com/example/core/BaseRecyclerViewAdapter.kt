package com.example.core

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ui.extensions.getView

abstract class BaseRecyclerViewAdapter<T : BaseViewHolder<R>, R> : RecyclerView.Adapter<T>() {

    var listItems: ArrayList<R> = arrayListOf()
        set(value) = run {
            field = value
            notifyDataSetChanged()
        }

    abstract var itemClickListener: (R) -> Unit

    abstract var itemLayout: Int

    abstract fun viewHolder(view: View): T

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): T = viewHolder(getView(itemLayout, parent))

    override fun getItemCount(): Int = listItems.size

    override fun onBindViewHolder(holder: T, position: Int) {
        holder.onBind(listItems[position], position)
    }
}