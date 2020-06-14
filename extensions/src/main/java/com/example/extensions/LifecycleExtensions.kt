package com.example.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.distinctUntilChanged

fun <T> LifecycleOwner.observeOnce(liveData: LiveData<T>, observer: (t : T) -> Unit) {
    liveData.observeOnce(this, observer)
}

fun <T> LifecycleOwner.observeDistinctUntilChanged(liveData: LiveData<T>, observer: (t : T) -> Unit) {
    observeOnce(liveData.distinctUntilChanged(), observer)
}