package com.example.extensions

fun Boolean.isTrue(func : () -> Unit) = run { if (this) func() else null }

fun Boolean.isFalse(func : () -> Unit) = run { if (this.not()) func() else null }