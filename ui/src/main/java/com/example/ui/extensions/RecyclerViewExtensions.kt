package com.example.ui.extensions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.Adapter<*>.getView(@LayoutRes layoutRes : Int, parent : ViewGroup) =
    LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)!!