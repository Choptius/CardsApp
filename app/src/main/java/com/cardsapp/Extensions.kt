package com.cardsapp

import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.addDivider(@DrawableRes id: Int, orientation: Int) {
    val drawable = ContextCompat.getDrawable(context, id)!!
    val dividerItemDecoration = DividerItemDecoration(context, orientation).apply {
        setDrawable(drawable)
    }
    addItemDecoration(dividerItemDecoration)
}

fun <T> Iterable<T>.replaced(oldElement: T, newElement: T) = map {
    if (it == oldElement) newElement
    else it
}
