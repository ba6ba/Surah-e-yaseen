package com.example.extensions

import android.content.Intent
import android.os.Bundle
import java.io.Serializable

fun Intent.get(string : String) = extras?.get(string)

fun Intent.hasDataWithKey(key : String) = hasExtra(key) && get(key) != null

fun Intent.hasAction(key : String) = key == action

fun <T : Any?> Bundle.hasOrNull(key : String, has : T.() -> Unit) = get(key).nonNull { has(get(key) as T) }