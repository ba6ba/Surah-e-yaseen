package com.example.tilawat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.core.BaseViewModel
import com.example.data.hasData
import com.example.data.reciters.ReciterWrapper
import com.example.data.reciters.toReciterWrapper
import com.example.data.reciters.toWrapperList
import com.example.extensions.hasData
import com.example.extensions.hasDataToShow
import com.example.reciters.RecitersProvider

typealias Reciters = List<ReciterWrapper>

class TilawatViewModel constructor(private val tilawatChapterProvider: TilawatChapterProvider,
    private val recitersProvider: RecitersProvider) : BaseViewModel() {

    val tilawatChapterData : MutableLiveData<TilawatChapterData> = tilawatChapterProvider.getTilawatChapterLiveData

    fun getTranslators() : LiveData<Reciters> = liveData {
        (recitersProvider.getReciters(this@TilawatViewModel)?.recitations.toWrapperList() ?: arrayListOf()).also {list ->
            list.hasDataToShow {
                setCurrentReciter(first())
            }
            emit(list)
        }
    }

    fun setCurrentReciter(reciter : ReciterWrapper) {
        tilawatChapterProvider.setCurrentReciter(reciter)
    }
}