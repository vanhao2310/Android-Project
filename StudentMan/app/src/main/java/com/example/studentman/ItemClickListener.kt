package com.example.studentman

import android.view.View

interface ItemClickListener {
    fun remove(position: Int, view: View)
    fun edit(position: Int)
}