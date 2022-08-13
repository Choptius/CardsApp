package com.cardsapp

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.widget.ImageButton
import androidx.annotation.MenuRes
import androidx.appcompat.widget.Toolbar
import com.cardsapp.databinding.SearchAndSelectionToolbarBinding


class SelectionToolbar(
    context: Context,
    private val attributeSet: AttributeSet?,
    private val defStyleAttr: Int
) : Toolbar(context, attributeSet, defStyleAttr) {

    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    constructor(context: Context) : this(context, null)

//    private val binding: SearchAndSelectionToolbarBinding
    var cancelSelectionIcon: Drawable? = null

    var selectionModeEnabled = false
        set(value) {
            field = value
//            onSelectionModeChanged()
        }


    @MenuRes
    var selectionMenuId: Int = 0

    var onBackPressed: () -> Unit = {}

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.search_and_selection_toolbar, this, true)
//        binding = SearchAndSelectionToolbarBinding.bind(this)

        initializeAttrs()

        setNavigationOnClickListener {
            Log.d("toolbar", "pressed")
            if (selectionModeEnabled) {
                selectionModeEnabled = false
            } else {
                onBackPressed.invoke()
            }
        }


    }

    private fun initializeAttrs() {
        val typedArray = context.obtainStyledAttributes(
            attributeSet,
            R.styleable.SelectionToolbar,
            defStyleAttr,
            0
        )
        cancelSelectionIcon =
            typedArray.getDrawable(R.styleable.SelectionToolbar_cancelSelectionIcon)
        selectionModeEnabled =
            typedArray.getBoolean(R.styleable.SelectionToolbar_selectionModeEnabled, false)
        selectionMenuId =
            typedArray.getInteger(R.styleable.SelectionToolbar_selectionMenu, R.menu.delete_menu)
        title = typedArray.getString(androidx.appcompat.R.styleable.ActionBar_title)
        typedArray.recycle()
    }

//    private fun onSelectionModeChanged() {
//        if (selectionModeEnabled) {
//            binding.searchView.visibility = GONE
//            inflateMenu(selectionMenuId)
//        } else {
//            binding.searchView.visibility = VISIBLE
//            menu.clear()
//        }
//    }

}   