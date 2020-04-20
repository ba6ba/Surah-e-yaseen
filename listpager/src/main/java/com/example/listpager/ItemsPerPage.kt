package com.example.listpager

import androidx.annotation.IntDef
import androidx.annotation.IntRange

const val ITEMS_PER_PAGE = 1

@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
@IntDef(value = [ITEMS_PER_PAGE])
@IntRange(from = ITEMS_PER_PAGE.toLong())
annotation class ItemsPerPage