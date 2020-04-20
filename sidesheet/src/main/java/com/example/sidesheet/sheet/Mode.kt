package com.example.sidesheet.sheet

enum class Mode {
    FULL,
    HALF,
    CUSTOM;

    companion object {
        fun get(value: Int) = when (value) {
            0 -> FULL
            1 -> HALF
            2 -> CUSTOM
            else -> CUSTOM
        }
    }
}