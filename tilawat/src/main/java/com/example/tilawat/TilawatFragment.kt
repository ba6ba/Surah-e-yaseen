package com.example.tilawat

import android.os.Bundle
import android.view.View
import com.example.core.BaseFragment
import com.example.core.BaseViewModel
import com.example.core.FlowData
import com.example.extensions.*
import com.example.extensions.show
import com.example.reciters.RecitersListView
import com.example.sidesheet.sheet.SideSheetStates
import com.example.ui.extensions.*
import kotlinx.android.synthetic.main.fragment_tilawat.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TilawatFragment : BaseFragment(R.layout.fragment_tilawat) {

    private var recitersListView: RecitersListView = RecitersListView()
    private val tilawatViewModel: TilawatViewModel by viewModel()

    companion object {
        fun newInstance() = TilawatFragment()
    }

    override fun getViewModel(): BaseViewModel? {
        return tilawatViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
        sideSheetListener()
    }

    private fun sideSheetListener() {
        sideSheet.onStateChangeListener = {
            FlowData.viewStateChangeLiveData.postValue(SideSheetStates.EXPAND == it)
            if (SideSheetStates.EXPAND == it) {
                sideSheet.bringToFront()
            } else {
                groupView.bringToFront()
            }
        }
    }

    private fun setObservers() {
        audioPlayer.onPlayClick = {
            updateCurrentAudioPlayingLayout(it)
            observeOnce(tilawatViewModel.fetchAudioForVerse(it)) {}
        }
        viewModelObservers()
    }

    private fun updateCurrentAudioPlayingLayout(number: Int) {
        currentlyPlayingLayout.isGone {
            animateByBottomToTopIn(requireContext()) { completed ->
                show()
            }
            audioPlayer.animateByBottomToTopOut(requireContext())
        }
        currentAudioNumber.text = number.inc().asFormatted(format = tilawatViewModel.formatToDisplayVerseCount)
    }

    private fun viewModelObservers() {
        observeOnce(tilawatViewModel.tilawatChapterData) { chapter ->
            updateViews(chapter)
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

        observeOnce(tilawatViewModel.networkError) {}

        observeOnce(tilawatViewModel.playAudioLiveData) {
            tilawatViewModel.playAudio()
        }

        observeOnce(tilawatViewModel.audioMetaData) { metaData ->
            audioPlayer.updatePlayer(metaData.toAudioPlayerMetaData())
        }
        observeOnce(tilawatViewModel.currentDurationLiveData) { duration ->
            audioPlayer.setCurrentTimeProgress(duration.toTimeStamp())
        }
    }

    private fun updateViews(chapter: TilawatChapterData) {
        //TODO - reciter not setting properly
        revelationPlace.text = chapter.revelationPlace
        surahNumber.value = chapter.surahNumber.toString()
        surahName.text = chapter.surahNameEnglish
        reciterName.setTextIfEmptyOrNull(chapter.reciter?.reciter?.reciterEngName)
        numberOfVerses.value = chapter.numberOfVerses.also {
            audioPlayer.maxValue = chapter.numberOfVerses
        }.toString().also {
            tilawatViewModel.formatToDisplayVerseCount = it.length.toString()
        }
    }
}