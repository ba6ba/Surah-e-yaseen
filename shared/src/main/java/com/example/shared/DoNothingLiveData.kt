package com.example.shared

import androidx.lifecycle.MutableLiveData

enum class Do {
    NOTHING
}

class DoNothingLiveData : MutableLiveData<Do>()