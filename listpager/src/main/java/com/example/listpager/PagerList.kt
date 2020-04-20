package com.example.listpager

data class PagerList<T> (var pageNo : Int, var item : MutableList<T>)