package com.example.extensions

const val DEFAULT_ARRAY_SIZE = 0

fun <T> ArrayList<T>.removeFrom(startIndex : Int = 0, finalIndex : Int) = kotlin.run{
    val newList = arrayListOf<T>()
    filterIndexed { index, t ->
        (index in startIndex..finalIndex && finalIndex <= lastIndex).isTrue {
            newList.add(t)
        }
        true
    }
    removeAll(newList)
    this
}

val <T> ArrayList<T>.firstIndex
    get() = indexOf(first())

fun <T> List<T>?.orEmptyArrayList() : ArrayList<T> = isNullOrEmpty().checkForTrue { arrayListOf<T>() } ?: this as ArrayList<T>