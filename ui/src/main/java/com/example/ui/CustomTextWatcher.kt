package com.example.ui

import android.text.Editable
import android.text.TextWatcher

interface CustomTextWatcher : TextWatcher {

    fun onTextChanged(text : String, after : Boolean = false)

    override fun afterTextChanged(s: Editable?) {
        onTextChanged(s.toString())
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        //
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        onTextChanged(s.toString(), true)
    }
}