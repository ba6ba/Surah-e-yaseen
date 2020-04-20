package com.example.sidesheet.sheet

enum class OpenFrom {
    START,
    END;

    companion object {
        fun get(value: Int) = when (value) {
            0 -> START
            1 -> END
            else -> END
        }
    }
}