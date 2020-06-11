package com.example.extensions

import android.content.Intent

fun Intent.get(string : String) = extras?.get(string)

fun Intent.hasDataWithKey(key : String) = hasExtra(key) && get(key) != null

fun Intent.hasAction(key : String) = key == action
