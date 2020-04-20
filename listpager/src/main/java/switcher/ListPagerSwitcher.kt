package switcher

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager2.widget.ViewPager2
import com.example.listpager.ListPager
import com.example.listpager.moveToNextItem
import com.example.listpager.moveToPreviousItem
import com.example.ui.com.example.ui.TranslationTextView
import com.example.ui.extensions.visibility
import kotlinx.android.synthetic.main.page_number_layout.view.*
import kotlinx.android.synthetic.main.switcher_layout.view.*

class ListPagerSwitcher @JvmOverloads constructor(
    context: Context, private val attributeSet: AttributeSet? = null, private val defStyleRes: Int = 0
) : SwitcherView(context, attributeSet, defStyleRes) {

    private lateinit var listPagerSwitcherView: ListPagerSwitcherView

    init {
        layoutView?.let {
            listPagerSwitcherView = ListPagerSwitcherView(it)
        }

        onCurrentPageCallback = {
            getSwitcherView.pageNumberView?.text = it.inc().toString()
        }
    }

    val getSwitcherView: ListPagerSwitcherView
        get() = listPagerSwitcherView

    inner class ListPagerSwitcherView(private val view: View) {

        init {
            next = view.next
            previous = view.previous
        }

        val pageNumberView: TranslationTextView?
            get() = view.pageNumber

        val pageNumberViewParent: ConstraintLayout?
            get() = view.pageNumberRoot
    }

    fun <T> setupWithListPager(listPager: ListPager<T>) {
        isRtl = listPager.isRtl
        setListenersWithViewPager2(listPager.pager)
    }

    fun setupWithViewPager2(viewPager2: ViewPager2) {
        setListenersWithViewPager2(viewPager2)
    }

    private fun setListenersWithViewPager2(viewPager2: ViewPager2) {
        onNextClickListener = {
            viewPager2.moveToNextItem()
        }

        onPreviousClickListener = {
            viewPager2.moveToPreviousItem()
        }

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                noOfPages = viewPager2.adapter?.itemCount ?: 0
                getSwitcherView.pageNumberViewParent?.visibility(noOfPages > 1)
                currentPage = position
            }
        })
    }
}