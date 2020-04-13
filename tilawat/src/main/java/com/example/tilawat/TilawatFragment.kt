package com.example.tilawat

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.sidesheet.sheet.SideSheetStates
import com.example.translators.TranslatorsList
import kotlinx.android.synthetic.main.fragment_tilawat.*

class TilawatFragment : Fragment(R.layout.fragment_tilawat) {

    private var translatorsList : TranslatorsList = TranslatorsList()

    companion object {
        fun newInstance() = TilawatFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sideSheet.apply {
            onStateChangeListener = {
                FlowData.viewStateChangeLiveData.postValue(SideSheetStates.EXPAND == it)
            }
            childLayoutInflateCompleteListener = {
                translatorsList.init(it)
            }
        }
    }
}