package com.cardsapp.multichoice

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class SimpleMultiChoiceHandler<T : Any> : MultiChoiceHandler<T>, MultiChoiceState<T> {

    private val checkedItems = HashSet<T>()
    private var items: List<T> = emptyList()
    private val stateFlow = MutableStateFlow(OnChanged())

    override fun setItemsFlow(coroutineScope: CoroutineScope, itemsFlow: Flow<List<T>>) {
        coroutineScope.launch {
            itemsFlow.collectLatest { list ->
                items = list
                removeDeletedItems(list)
                notifyUpdates()
            }
        }
    }

    override fun listen(): Flow<MultiChoiceState<T>> {
        return stateFlow.map { this }
    }

    override fun isChecked(item: T): Boolean {
        return checkedItems.contains(item)
    }

    override fun toggle(item: T) {
        if (isChecked(item)) {
            clear(item)
        } else {
            check(item)
        }
    }

    override fun check(item: T) {
        if (!exists(item)) return
        checkedItems.add(item)
        notifyUpdates()
    }

    override fun clear(item: T) {
        if (!exists(item)) return
        checkedItems.remove(item)
        notifyUpdates()
    }

    override fun selectAll() {
        checkedItems.addAll(items.map { it })
        notifyUpdates()
    }

    override fun clearAll() {
        checkedItems.clear()
        notifyUpdates()
    }

    override val totalCheckedCount: Int
        get() = checkedItems.size

    private fun exists(item: T): Boolean {
        return items.any { it == item }
    }

    private fun removeDeletedItems(items: List<T>) {
        val existingItems = HashSet(items)
        val iterator = checkedItems.iterator()
        while (iterator.hasNext()) {
            val item = iterator.next()
            if (!existingItems.contains(item)) {
                iterator.remove()
            }
        }
    }

    private fun notifyUpdates() {
        stateFlow.value = OnChanged()
    }

    private class OnChanged
}