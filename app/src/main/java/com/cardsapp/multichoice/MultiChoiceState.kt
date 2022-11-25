package com.cardsapp.multichoice
/**
 * Represents the current state of checked/unchecked items.
 */
interface MultiChoiceState<T> {

    /**
     * Whether the item with the specified ID is checked or not.
     */
    fun isChecked(item: T): Boolean

    /**
     * The total number of checked items
     */
    val totalCheckedCount: Int

}