package com.example.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

fun <T> LifecycleOwner.observeOnce(liveData: LiveData<T>, observer: (t : T) -> Unit) {
    liveData.observeOnce(this, observer)
}