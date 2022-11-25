package com.cardsapp.presentation

import kotlinx.coroutines.flow.Flow

fun interface OnSelectionModeChangedListener {
    fun onSelectionModeChanged(enabled: Boolean)
}