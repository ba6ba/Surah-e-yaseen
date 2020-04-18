package com.example.extensions


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