package com.cardsapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FolderInfoRvDto(
    val name: String,
    val size: Int,
    var selected: Boolean = false
) : Parcelable
