package com.cardsapp

fun interface ItemClickListener<T> {
    fun onItemClick(obj: T)
}