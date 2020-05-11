package com.example.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.nonNullObserver(lifecycleOwner: LifecycleOwner, observer: (t : T) -> Unit) {
    observe(lifecycleOwner, Observer {
        it?.let {
            observer(it)
        }
    })
}

fun <T> MediatorLiveData<T>.observe(lifecycleOwner: LifecycleOwner, observer: (t : T) -> Unit) =
    observe(lifecycleOwner, Observer {
        it?.let(observer)
    })

fun <T> MediatorLiveData<T>.nonNull() =
    MediatorLiveData<T>().apply {
        addSource(this) {
            it?.let{
                value = it
            }
        }
    }

fun LiveData<Boolean>.isTrueObserver(lifecycleOwner: LifecycleOwner, observer: () -> Unit) {
    observe(lifecycleOwner, Observer {
        it?.let {
            if (it) {
                observer()
            }
        }
    })
}

fun LiveData<Boolean>.isFalseObserver(lifecycleOwner: LifecycleOwner, observer: () -> Unit) {
    observe(lifecycleOwner, Observer {
        it?.let {
            if (it.not()) {
                observer()
            }
        }
    })
}

fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: (t : T) -> Unit) {
    removeObservers(lifecycleOwner)
    nonNullObserver(lifecycleOwner, observer)
}