package com.example.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.distinctUntilChanged

fun <T> LifecycleOwner.observeOnceOnLifecycle(liveData: LiveData<T>, observer: (t : T) -> Unit) {
    liveData.observeOnceOnLifecycle(this, observer)
}

fun <T> LifecycleOwner.observeDistinctUntilChangedOnLifecycle(liveData: LiveData<T>, observer: (t : T) -> Unit) {
    observeOnceOnLifecycle(liveData.distinctUntilChanged(), observer)
}