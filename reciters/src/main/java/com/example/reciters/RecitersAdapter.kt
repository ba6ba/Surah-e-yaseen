package com.example.reciters

import android.view.View
import com.example.core.BaseRecyclerViewAdapter
import com.example.core.BaseViewHolder
import com.example.data.reciters.ReciterWrapper
import com.example.extensions.isTrue
import com.example.ui.horizontalcell.CustomHorizontalCellState
import kotlinx.android.synthetic.main.translators_item_layout.view.*

class RecitersAdapter : BaseRecyclerViewAdapter<RecitersAdapter.TranslatorsViewHolder, ReciterWrapper>() {

    override lateinit var itemClickListener: (ReciterWrapper) -> Unit
    override var itemLayout: Int = R.layout.translators_item_layout

    inner class TranslatorsViewHolder(view: View) : BaseViewHolder<ReciterWrapper>(view) {
        override fun onBind(item: ReciterWrapper, position: Int) {
            itemView.translatorItem.apply {
                text = item.reciter.reciterEngName
                subText = item.reciter.style ?: ""
                updateState(item.selected)
                onClickListener = { text, state ->
                    listItems.forEach { it.selected = it.reciter.id == item.reciter.id }
                    notifyDataSetChanged()
                    (CustomHorizontalCellState.HIGHLIGHTED == state).isTrue {
                        itemClickListener(item)
                    }
                }
            }
        }
    }

    override fun viewHolder(view: View) = TranslatorsViewHolder(view)
}