package com.example.translators

import android.view.View
import com.example.core.BaseRecyclerViewAdapter
import com.example.core.BaseViewHolder
import com.example.extensions.isTrue
import com.example.ui.horizontalcell.CustomHorizontalCellState
import kotlinx.android.synthetic.main.translators_item_layout.view.*

class TranslatorsAdapter : BaseRecyclerViewAdapter<TranslatorsAdapter.TranslatorsViewHolder, Translator>() {

    override lateinit var itemClickListener: (Translator) -> Unit
    override var itemLayout: Int = R.layout.translators_item_layout

    inner class TranslatorsViewHolder(view: View) : BaseViewHolder<Translator>(view) {
        override fun onBind(item: Translator, position: Int) {
            itemView.translatorItem.apply {
                text = item.translator
                updateState(item.selected)
                onClickListener = { text, state ->
                    (CustomHorizontalCellState.HIGHLIGHTED == state).isTrue {
                        itemClickListener.invoke(item)
                    }
                    listItems.forEach { it.selected = it.id == item.id }
                    notifyDataSetChanged()
                }
            }
        }
    }

    override fun viewHolder(view: View) = TranslatorsViewHolder(view)
}