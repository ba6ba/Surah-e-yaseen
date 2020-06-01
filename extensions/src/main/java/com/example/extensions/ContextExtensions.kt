package com.example.extensions

import android.content.Intent

fun Intent.get(string : String) = extras?.get(string)