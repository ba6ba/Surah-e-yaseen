package com.example.tilawat

import android.os.Bundle
import android.view.View
import com.example.core.BaseFragment
import com.example.core.BaseViewModel
import com.example.core.FlowData
import com.example.extensions.observeOnce
import com.example.extensions.setTextIfEmptyOrNull
import com.example.sidesheet.sheet.SideSheetStates
import com.example.reciters.RecitersListView
import kotlinx.android.synthetic.main.fragment_tilawat.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TilawatFragment : BaseFragment(R.layout.fragment_tilawat) {

    private var recitersListView : RecitersListView = RecitersListView()
    private val tilawatViewModel : TilawatViewModel by viewModel()

    companion object {
        fun newInstance() = TilawatFragment()
    }

    override fun getViewModel(): BaseViewModel? {
        return tilawatViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
        audioPlayer.apply {
            init(this@TilawatFragment)
            onPlayClick = {
                observeOnce(tilawatViewModel.fetchAudioForVerse(it)) {}
            }
        }
    }

    private fun setObservers() {
        sideSheet.onStateChangeListener = {
            FlowData.viewStateChangeLiveData.postValue(SideSheetStates.EXPAND == it)
            if (SideSheetStates.EXPAND == it) {
                sideSheet.bringToFront()
            }
            else {
                groupView.bringToFront()
            }
        }

        observeOnce(tilawatViewModel.getTranslators()) { dataList ->
            recitersListView.apply {
                itemClickListener = {
                    tilawatViewModel.setCurrentReciter(it)
                    sideSheet.collapseTrigger()
                }
                init(sideSheet.childView, dataList)
            }
        }

        observeOnce(tilawatViewModel.tilawatChapterData) { chapter ->
            updateViews(chapter)
        }

        observeOnce(tilawatViewModel.networkError) {
            //
        }

        observeOnce(tilawatViewModel.audioItems) {
            tilawatViewModel.playAudio(audioPlayer.counterValue)
        }
    }

    private fun updateViews(chapter: TilawatChapterData) {
        revelationPlace.text = chapter.revelationPlace
        numberOfVerses.value = chapter.numberOfVerses.toString()
        surahNumber.value = chapter.surahNumber.toString()
        surahName.text = chapter.surahNameEnglish
        reciterName.setTextIfEmptyOrNull(chapter.reciter?.reciter?.reciterEngName)
    }
}