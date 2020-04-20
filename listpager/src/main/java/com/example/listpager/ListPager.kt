package com.example.listpager

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.annotation.LayoutRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.extensions.isTrue
import com.example.ui.extensions.inflateAndGetStyleAttributes
import com.example.ui.extensions.isValidResourceId
import kotlinx.android.synthetic.main.list_pager.view.*

abstract class ListPager<T> @JvmOverloads constructor(
    context: Context,
    private val attributeSet: AttributeSet? = null,
    private val defStyleRes: Int = 0
) :
    ConstraintLayout(context, attributeSet, defStyleRes) {

    private var listPagerManager: ListPagerManager<T> = ListPagerManager()
    abstract var itemViewHolder: (View) -> RecyclerView.ViewHolder
    abstract var itemViewHolderCallBack: (RecyclerView.ViewHolder, MutableList<T>, Int) -> Unit

    var isRtl: Boolean = false
    val pager: ViewPager2
        get() = listViewPager

    @LayoutRes
    private val viewPagerLayoutResId = R.layout.list_pager_item_layout
    @LayoutRes
    var itemLayout: Int = 0
    @ItemsPerPage
    var numberOfItemsPerPage = ITEMS_PER_PAGE
        set(value) {
            field = value
            listPagerManager.numberOfItemsPerPage = value
        }

    init {
        inflateAndGetStyleAttributes(R.layout.list_pager, R.styleable.ListPager, attributeSet) {
            try {
                numberOfItemsPerPage = getInt(R.styleable.ListPager_itemsPerPage, 1)
                isRtl = getBoolean(R.styleable.ListPager_isRtl, false)
                itemLayout = getResourceId(R.styleable.ListPager_listItemLayout, -1)
            } finally {
                recycle()
            }
        }
    }

    fun populateData(arrayList: ArrayList<T>) = listPagerManager.populateData(arrayList)

    protected fun createViews() {
        itemLayout.isValidResourceId().isTrue {
            listPagerManager.apply {
                createViewPagerAdapter(viewPagerLayoutResId,
                    viewHolder = { ListPagerViewPagerHolder(it, createPagerItemAdapter()) },
                    viewHolderCallback = { holder, list, position ->
                        (holder as ListPagerViewPagerHolder<T>).onBind((list as ArrayList<PagerList<T>>)[position])
                    },
                    viewPagerAdapter = {
                        configureViewPager(this)
                    })
            }
        }
    }

    private fun configureViewPager(listPagerAdapter: ListPagerAdapter<T>) {
        listViewPager.apply {
            layoutDirection = if (isRtl) ViewPager2.LAYOUT_DIRECTION_RTL else ViewPager2.LAYOUT_DIRECTION_LTR
            adapter = listPagerAdapter
            setPageTransformer(transformer)
            registerOnPageChangeCallback(viewPagerChangeCallBack)
        }
    }

    private fun createPagerItemAdapter() =
        listPagerManager.createViewPagerItemAdapter(itemLayout,
            itemViewHolderCallBack = itemViewHolderCallBack,
            itemViewHolder = { itemViewHolder(it) })

    private val viewPagerChangeCallBack = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            onPageChangeCallback(position)
        }
    }

    open fun onPageChangeCallback(position: Int) {
        //
    }

    fun navigateNext() {
        listViewPager.moveToNextItem()
    }

    fun navigatePrevious() {
        listViewPager.moveToPreviousItem()
    }
}