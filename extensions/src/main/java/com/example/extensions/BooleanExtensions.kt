package com.example.extensions

typealias Condition = Boolean.() -> Unit

inline fun Boolean.isTrue(crossinline func : Condition) = run { if (this) func(this) else null }

inline fun Boolean.isFalse(crossinline func : Condition) = run { if (this.not()) func(this.not()) else null }

inline fun <R> Boolean?.checkForTrue(crossinline action : () -> R) = if (this == true) action() else null

inline fun <R> Boolean?.checkForFalse(crossinline action : () -> R) = if (this == false) action() else null