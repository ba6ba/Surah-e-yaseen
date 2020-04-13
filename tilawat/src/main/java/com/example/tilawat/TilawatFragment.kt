package com.example.tilawat

import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import com.example.core.FlowData
import kotlinx.android.synthetic.main.fragment_tilawat.*

class TilawatFragment : Fragment(R.layout.fragment_tilawat) {

    companion object {
        fun newInstance() = TilawatFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sideSheet.onStateChangeListener = {
            FlowData.viewStateChangeLiveData.postValue(it)
        }
    }
}