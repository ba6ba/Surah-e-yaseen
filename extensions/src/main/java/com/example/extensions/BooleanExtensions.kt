package com.example.extensions

typealias Condition = Boolean.() -> Unit

fun Boolean.isTrue(func : Condition) = run { if (this) func() else null }

fun Boolean.isFalse(func : Condition) = run { if (this.not()) func() else null }