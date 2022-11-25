package com.cardsapp.presentation

import android.os.Parcelable
import com.cardsapp.domain.FolderInfo
import com.cardsapp.domain.FolderWithSize
import kotlinx.parcelize.Parcelize


data class SelectableFolderWithSize(
    val folderWithSize: FolderWithSize,
    override val selected: Boolean = false
) : Selectable

interface Selectable {
    val selected: Boolean
}

