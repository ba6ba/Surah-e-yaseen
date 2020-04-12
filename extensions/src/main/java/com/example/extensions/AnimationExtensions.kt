package com.example.extensions

import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils

fun View.doAnimation(context: Context, animRes : Int) = startAnimation(AnimationUtils.loadAnimation(context, animRes))