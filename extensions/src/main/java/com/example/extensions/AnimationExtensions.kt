package com.example.extensions

import android.content.Context
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils

fun View.doAnimation(context: Context, animRes: Int, animationCompleted: ((Boolean) -> Unit)? = null) =
    startAnimation(AnimationUtils.loadAnimation(context, animRes)
        .apply {
            animationCompleted?.let {
                setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationRepeat(animation: Animation?) {
                        //
                    }

                    override fun onAnimationEnd(animation: Animation?) {
                        animationCompleted(true)
                    }

                    override fun onAnimationStart(animation: Animation?) {
                        animationCompleted(false)
                    }
                })
            }
        })