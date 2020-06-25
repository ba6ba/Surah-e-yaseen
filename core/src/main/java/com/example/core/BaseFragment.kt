package com.example.core

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.example.data.GenericError
import com.example.extensions.observeOnceOnLifecycle
import com.example.extensions.showToast

abstract class BaseFragment(@LayoutRes private val layoutRes: Int) : Fragment(layoutRes) {

    abstract fun getViewModel(): BaseViewModel?

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeErrorHandler()
    }

    private fun observeErrorHandler() {
        getViewModel()?.apply {
            observeOnceOnLifecycle(errorLiveData) { genericError ->
                if (GenericError.Severity.HIGH == genericError.severity) {
                    // open Fragment to block user from doing any actions
                } else {
                    showToast(genericError.message)
                }
            }
        }
    }
}