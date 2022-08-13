package com.cardsapp

import android.graphics.drawable.Drawable
import android.view.MenuItem
import android.view.View
import androidx.annotation.MenuRes
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.cardsapp.databinding.SearchAndSelectionToolbarBinding

class SearchAndSelectionToolbar(private val binding: SearchAndSelectionToolbarBinding) {
    private val context = binding.root.context

    var defaultTitle: CharSequence = binding.toolbar.title
        set(value) {
            field = value
            binding.toolbar.title = value
        }
    var defaultNavigationIcon: Drawable = binding.toolbar.navigationIcon ?:
        ContextCompat.getDrawable(context, R.drawable.ic_arrow_back)!!

    //selection mode related properties
    var selectionModeEnabled = false
        set(value) {
            field = value
            if(value) {
                enableSelectionMode()
            } else {
                disableSelectionMode()
            }
        }

    @MenuRes
    var selectionMenuRes: Int? = null

    var onMenuItemClickListener: Toolbar.OnMenuItemClickListener? = null

    var onCancelSelectionButtonClick = { }

    //default mode properties
    var onBackPressedListener: View.OnClickListener? = null
        set(value) {
            field = value
            binding.toolbar.setNavigationOnClickListener(value)
        }

    var onQueryTextListener: SearchView.OnQueryTextListener? = null
        set(value) {
            field = value
            binding.searchView.setOnQueryTextListener(onQueryTextListener)
        }

    fun showSelectedItemsCount(count: Int) {
        if (selectionModeEnabled) {
            binding.toolbar.title = "$count"
        }
    }

    private fun enableSelectionMode() {
        with(binding) {
            toolbar.navigationIcon =
                ContextCompat.getDrawable(context, R.drawable.ic_cross)

            searchView.visibility = View.GONE

            selectionMenuRes?.let { toolbar.inflateMenu(it) }
            toolbar.setOnMenuItemClickListener(onMenuItemClickListener)
            toolbar.setNavigationOnClickListener {
                disableSelectionMode()
                onCancelSelectionButtonClick.invoke()
            }
        }
    }

    private fun disableSelectionMode() {
        with(binding) {
            toolbar.navigationIcon = defaultNavigationIcon
            searchView.visibility = View.VISIBLE
            toolbar.title = defaultTitle
            toolbar.menu.clear()
            toolbar.setNavigationOnClickListener(onBackPressedListener)
        }
    }

}