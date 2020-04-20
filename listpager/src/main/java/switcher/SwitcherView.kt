package switcher

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.annotation.LayoutRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.extensions.isTrue
import com.example.listpager.R
import com.example.ui.extensions.*
import kotlinx.android.synthetic.main.pager_switcher_layout.view.*

abstract class SwitcherView @JvmOverloads constructor(
    context: Context, private val attributeSet: AttributeSet? = null, private val defStyleRes: Int = 0
) :
    ConstraintLayout(context, attributeSet, defStyleRes) {

    lateinit var onPreviousClickListener: () -> Unit
    lateinit var onNextClickListener: () -> Unit
    var noOfPages: Int = 0
    var onCurrentPageCallback : ((Int) -> Unit) ? = null
    var layoutView: View? = null
    protected var isRtl : Boolean = false

    @LayoutRes
    var stubResId: Int = -1
        set(value) {
            field = value
            value.isValidResourceId().isTrue {
                layoutView = layoutStub.apply {
                    layoutResource = value
                }.inflate()
            }
        }

    var next: View? = null
        set(value) {
            field = value
            next?.setOnClickListener {
                if (isRtl.not()) {
                    onNextClickListener()
                }
                else {
                    onPreviousClickListener()
                }
            }
        }

    var previous: View? = null
        set(value) {
            field = value
            previous?.setOnClickListener {
                if (isRtl.not()) {
                    onPreviousClickListener()
                }
                else {
                    onNextClickListener()
                }
            }
        }

    var currentPage: Int = 0
        set(value) {
            field = value
            previous?.visibility(getVisibilityForPreviousIcon)
            next?.visibility(getVisibilityForNextIcon)
            onCurrentPageCallback?.invoke(value)
        }

    private val getVisibilityForPreviousIcon : Boolean
        get() = if (isRtl.not()) currentPage > 0 else currentPage != noOfPages - 1

    private val getVisibilityForNextIcon : Boolean
        get() = if (isRtl.not()) currentPage != noOfPages - 1 else currentPage > 0

    init {
        inflateAndGetStyleAttributes(
            R.layout.pager_switcher_layout,
            R.styleable.SwitcherView, attributeSet) {
            try {
                stubResId = getResourceId(R.styleable.SwitcherView_layout, -1)
                isRtl = getBoolean(R.styleable.SwitcherView_isRtl, false)
            } finally {
                recycle()
            }
        }
    }
}