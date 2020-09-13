package com.example.batmanmoviesmvvm.extension

import android.view.View
import androidx.constraintlayout.widget.Group

fun View.visible(): View {
    this.visibility = View.VISIBLE
    if (this is Group) {
        this.requestLayout()
    }
    return this
}

fun View.inVisible(): View {
    this.visibility = View.INVISIBLE
    if (this is Group) {
        this.requestLayout()
    }
    return this
}

fun View.gone(): View {
    this.visibility = View.GONE
    if (this is Group) {
        this.requestLayout()
    }
    return this
}

fun View.ifVis(shouldShow :Boolean): View {
    this.visibility = if (shouldShow) View.VISIBLE else View.GONE
    if (this is Group) {
        this.requestLayout()
    }
    return this
}