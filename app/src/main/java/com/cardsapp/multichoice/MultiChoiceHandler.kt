package com.cardsapp.multichoice

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

/**
 * The logic of changing check state.
 * Also this interface provides a method for listening the multi-choice state.
 */
interface MultiChoiceHandler<T : Any> {

    /**
     * Set the list flow which will be observed by the handler in order
     * to keep the inner state up-to-date. Usually call this method in the init
     * block of your view-model.
     */
    fun setItemsFlow(coroutineScope: CoroutineScope, itemsFlow: Flow<List<T>>)

    /**
     * Observe the current state of multi-choice
     */
    fun listen(): Flow<MultiChoiceState<T>>

    /**
     * Invert selection for the specified item.
     */
    fun toggle(item: T)

    /**
     * Check all items.
     */
    fun selectAll()

    /**
     * Uncheck all items.
     */
    fun clearAll()

    /**
     * Check the specified item in the list
     */
    fun check(item: T)

    /**
     * Uncheck the specified item in the list.
     */
    fun clear(item: T)

}
