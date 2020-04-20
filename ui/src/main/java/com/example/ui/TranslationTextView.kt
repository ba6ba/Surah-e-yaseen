package com.example.ui.com.example.ui

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.example.translations.TranslationManager
import com.example.ui.R

open class TranslationTextView @JvmOverloads constructor(
    context: Context, private val attrs: AttributeSet? = null, defStyleAttr: Int = android.R.attr.textViewStyle
) : AppCompatTextView(context, attrs, defStyleAttr) {

    private val translationManager : TranslationManager = TranslationManager.getInstance(context)

    var textKey: String? = ""
        set(value) {
            field = value
            text = translationManager.getTranslatedText(field)
        }

    init {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        getAttributes(attrs)
        if (isInEditMode) {
            return
        }
    }

    fun setTextFromTranslationKey(translationKey: String) {
        if (translationKey.isEmpty().not()) {
            textKey = translationKey
        }
    }

    private fun getAttributes(attrs: AttributeSet?) {
        context.theme.obtainStyledAttributes(attrs,
            R.styleable.TranslationTextView, 0, 0).
            apply {
                try {
                    textKey = getString(R.styleable.TranslationTextView_text)
                } finally {
                    recycle()
                }
        }
    }
}
