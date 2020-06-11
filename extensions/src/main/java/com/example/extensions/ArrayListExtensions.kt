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

fun <T> List<T>?.hasDataToShow(list : List<T>.() -> Unit) = if(isNullOrEmpty().not()) list(this!!) else null

fun <T> List<T>?.hasData(list: (List<T>) -> Unit) : List<T>? =
    if(isNullOrEmpty().not()) {
        list(this!!)
        this
    }
    else this!!

fun <T> listWithCapacity(capacity : Int) =
    arrayListOf<T>()
        .apply { ensureCapacity(capacity) }

fun <T> List<T>.hasItem(index : Int) : T? = if (has(index)) this[index] else null

fun <T> List<T>.has(index : Int) : Boolean = index.lessThan(size)

val <T> ArrayList<T>.validLastIndex
    get() = if (size.greaterThan(0)) lastIndex else size

val <T> List<T>.castToArrayList : ArrayList<T>
    get() = this as ArrayList<T>

val <T> List<T>.castToMutableList : MutableList<T>
    get() = this as MutableList<T>