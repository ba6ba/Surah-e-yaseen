package com.example.splash

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.core.BaseFragment
import com.example.core.BaseViewModel

class SplashFragment : BaseFragment(R.layout.fragment_splash) {
    override fun <M : BaseViewModel> getViewModel(): M? {
        return null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler().postDelayed({
            findNavController().navigate(R.id.splashFragment_to_mainFragment)
        }, 3000L)
    }
}