package com.example.recitation

import androidx.lifecycle.MutableLiveData
import com.example.extensions.isTrue
import com.example.listpager.PagerList
import java.util.HashMap
import kotlin.math.ceil

class RecitationItemsProvider {

    fun getVerses() = arrayListOf(
        Verse("english1", "سیدھے راستے کی طرف", "arabic1", 1),
        Verse("english2", "سیدھے راستے کی طرف", "arabic2", 2),
        Verse("english3", "سیدھے راستے کی طرف", "arabic3", 3),
        Verse("english4", "سیدھے راستے کی طرف", "arabic4", 4),
        Verse("english5", "سیدھے راستے کی طرف", "arabic5", 5)
    )

    val recitationsLiveData : MutableLiveData<ArrayList<Verse>> = MutableLiveData()

    init {
        recitationsLiveData.postValue(getVerses())
    }
}