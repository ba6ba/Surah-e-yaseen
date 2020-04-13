package com.example.translators

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.extensions.isTrue
import com.example.ui.extensions.getView
import com.example.ui.horizontalcell.CustomHorizontalCellState
import kotlinx.android.synthetic.main.translators_item_layout.view.*

class TranslatorsAdapter : RecyclerView.Adapter<TranslatorsAdapter.TranslatorsViewHolder>() {

    var translatorsList: ArrayList<Translator> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    lateinit var onClickListener : (Any) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TranslatorsViewHolder =
        TranslatorsViewHolder(getView(R.layout.translators_item_layout, parent))

    override fun getItemCount() = translatorsList.size

    override fun onBindViewHolder(holder: TranslatorsViewHolder, position: Int) {
        holder.onBind(translatorsList[position])
    }

    inner class TranslatorsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun onBind(translator: Translator) {
            itemView.apply {
                translatorItem.text = translator.translator
                setOnClickListener {
                    translatorItem.onClickListener = { text, state ->
                        (CustomHorizontalCellState.HIGHLIGHTED == state).isTrue {
                            onClickListener.invoke(text)
                        }
                    }
                }
            }
        }
    }
}