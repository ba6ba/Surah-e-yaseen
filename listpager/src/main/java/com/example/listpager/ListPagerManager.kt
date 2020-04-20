package com.example.listpager

import android.view.View
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.math.ceil

class ListPagerManager<T> {

    private lateinit var pagerAdapter : ListPagerAdapter<T>
    @ItemsPerPage
    var numberOfItemsPerPage : Int = 0
    set(value) {
        if (value < ITEMS_PER_PAGE) {
            throw IllegalArgumentException(
                "Items per page must be ITEMS_PER_PAGE default value or a number > 1"
            )
        }
        field = value
    }

    fun createViewPagerAdapter(@LayoutRes viewPagerItemLayoutResId: Int,
        viewPagerAdapter: ListPagerAdapter<T>.() -> Unit, viewHolder: (View) -> RecyclerView.ViewHolder,
        viewHolderCallback: (RecyclerView.ViewHolder, MutableList<T>, Int) -> Unit
        ) = kotlin.run {
        viewPagerAdapter(ListPagerAdapter(viewPagerItemLayoutResId, viewHolder, viewHolderCallback).also {
            pagerAdapter = it
        })
    }

    fun createViewPagerItemAdapter(@LayoutRes itemLayoutResId: Int, itemViewHolder : (View) -> RecyclerView.ViewHolder,
        itemViewHolderCallBack: (RecyclerView.ViewHolder, MutableList<T>, Int) -> Unit) =
        ListPagerItemsAdapter(itemLayoutResId, itemViewHolder, itemViewHolderCallBack)

    fun populateData(arrayList: ArrayList<T>) = kotlin.run {
        if (this::pagerAdapter.isInitialized) {
            pagerAdapter.itemsList = toPagerList(getList(arrayList))
        } else {
            throw IllegalStateException("must pass item layout to inflate inside the list pager")
        }
    }

    private fun getList(arrayList: ArrayList<T>, @ItemsPerPage numberOfItems: Int = numberOfItemsPerPage) = run {
        val numberOfPages = ceil(arrayList.size.toDouble().div(numberOfItems))
        var range = 0..numberOfItems
        hashMapOf<Int, MutableList<T>>().apply {
            for (i in 0 until numberOfPages.toInt()) {
                if (range.last <= arrayList.size) {
                    put(i + 1, arrayList.subList(range.first, range.last))
                    range = range.last..range.last.plus(numberOfItems)
                }
                else if (range.first <= arrayList.size) {
                    put(i + 1, arrayList.subList(range.first, arrayList.size))
                }
            }
        }
    }

    private fun toPagerList(hashMap: HashMap<Int, MutableList<T>>) = kotlin.run {
        mutableListOf<PagerList<T>>().apply {
            for (i in hashMap.keys.indices) {
                add(PagerList(pageNo = i + 1, item = hashMap[i + 1]!!))
            }
        }
    }

}