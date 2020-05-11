package com.example.ui.extensions

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ScrollView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.Adapter<*>.getView(@LayoutRes layoutRes : Int, parent : ViewGroup) =
    LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)!!

fun RecyclerView.scrollToTop() {
    (layoutManager as? LinearLayoutManager)?.scrollToPositionWithOffset(0,0)
}