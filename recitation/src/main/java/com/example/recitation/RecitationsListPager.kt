package com.example.recitation

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.listpager.ListPager

class RecitationsListPager @JvmOverloads constructor(
    context: Context,
    private val attributeSet: AttributeSet? = null,
    private val defStyleRes: Int = 0
) : ListPager<Verse>(context, attributeSet, defStyleRes) {

    lateinit var onItemClickListener : (Verse) -> Unit

    init {
        createViews()
    }

    override var itemViewHolder: (View) -> RecyclerView.ViewHolder = { view ->
        RecitationItemViewHolder(view).apply {
            onItemClickListener = this@RecitationsListPager.onItemClickListener
        }
    }

    override var itemViewHolderCallBack: (RecyclerView.ViewHolder, MutableList<Verse>, Int) -> Unit = { holder, list, position ->
        (holder as RecitationItemViewHolder).onBind(list[position])
    }
}