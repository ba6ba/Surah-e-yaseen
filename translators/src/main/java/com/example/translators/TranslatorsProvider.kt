package com.example.translators

import androidx.lifecycle.MutableLiveData
import com.example.extensions.removeFrom

class TranslatorsProvider {
    private val translatorsListLiveData : MutableLiveData<ArrayList<Translator>> = MutableLiveData()
    var translatorsList = arrayListOf(
        Translator(id = "1", translator = "Mishari Rashid Al Asafy"),
        Translator(id = "2", translator = "Mishari Rashid Al Asafy"),
        Translator(id = "3", translator = "Mishari Rashid Al Asafy"),
        Translator(id = "4", translator = "Mishari Rashid Al Asafy"),
        Translator(id = "5", translator = "Mishari Rashid Al Asafy"),
        Translator(id = "6", translator = "Mishari Rashid Al Asafy"),
        Translator(id = "7", translator = "Mishari Rashid Al Asafy"),
        Translator(id = "8", translator = "Mishari Rashid Al Asafy")
    )
    var includeEnglishTranslators: Boolean = false
    var onTranslationSelection: MutableLiveData<Translator> = MutableLiveData()
    init {
        translatorsListLiveData.postValue(translatorsList.removeFrom(finalIndex = 4))
    }

    fun translatorsList() : MutableLiveData<ArrayList<Translator>> = translatorsListLiveData
}

/*
class TranslatorsProvider private constructor(private val builder: Builder) {

    private val numberOfTranslators: Int = builder.numberOfTranslators
    private val includeEnglishTranslators: Boolean = builder.includeEnglishTranslators

    companion object {
        internal fun build(block : Builder.() -> Unit) = Builder().apply(block).build()
    }

    internal class Builder {
        var numberOfTranslators: Int = 0
        var includeEnglishTranslators: Boolean = false

        fun build() = TranslatorsProvider(this)
    }
}*/
