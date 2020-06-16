package com.example.tilawat

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.core.BaseFragment
import com.example.core.BaseViewModel
import com.example.shared.FlowData
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
            tilawatViewModel.doFetchOrPlay(it)
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
        setVerseCounterText(number)
    }

    private fun setVerseCounterText(number: Number) {
        currentAudioNumber.text = number.toInt().inc().asFormatted(format = tilawatViewModel.formatToDisplayVerseCount)
    }

    private fun viewModelObservers() {
        observeOnce(tilawatViewModel.tilawatChapterData) { chapter ->
            chapter.hasValidReciter.isTrue {
                updateViews(chapter)
            } ?: observeReciters()
        }

        observeOnce(tilawatViewModel.audioMetaData) { metaData ->
            audioPlayer.updatePlayer(metaData)
            setVerseCounterText(metaData.number)
        }

        observeDistinctUntilChanged(tilawatViewModel.currentDurationLiveData) { duration ->
            audioPlayer.updatePlayerProgress(duration)
        }
    }

    private fun observeReciters() {
        observeDistinctUntilChanged(tilawatViewModel.getTranslators()) { dataList ->
            recitersListView.apply {
                itemClickListener = {
                    tilawatViewModel.setCurrentReciter(it)
                    sideSheet.collapseTrigger()
                }
                init(sideSheet.childView, dataList)
            }
        }
    }

    private fun updateViews(chapter: TilawatChapterData) {
        revelationPlace.text = chapter.revelationPlace
        surahNumber.value = chapter.surahNumber.toString()
        surahName.text = chapter.surahNameEnglish
        reciterName.setTextIfEmptyOrNull(chapter.reciterName)
        numberOfVerses.value = chapter.numberOfVerses.also {
            audioPlayer.maxCounterValue = chapter.numberOfVerses
        }.toString().also {
            tilawatViewModel.formatToDisplayVerseCount = it.length.toString()
        }
    }
}