package com.cardsapp

import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ItemSelector<K : Any> {

    private val _selectedKeys = MutableStateFlow<Set<K>>(emptySet())
    val selectedKeys: StateFlow<Set<K>> = _selectedKeys

    fun select(key: K) {
        _selectedKeys.value += key
    }

    fun unSelect(key: K) {
        _selectedKeys.value -= key
    }

    fun clear() {
        _selectedKeys.value = emptySet()
    }
    fun isSelected(key: K) = key in _selectedKeys.value

    fun toggleSelectedState(key: K) {
        Log.d("key", key.toString())
        if (isSelected(key)) {
            unSelect(key)
        } else {
            select(key)
        }
    }
}