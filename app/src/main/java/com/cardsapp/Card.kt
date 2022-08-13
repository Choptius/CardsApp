package com.cardsapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Card(
    val id: Int,
    val moduleName: String,
    val wordOrSentence: String,
    val definedWord: String = "",
    val infinitiveOfDefinedWord: String = "",
    val definition: String,
    val examples: List<String>,
    val pronunciation1: Pronunciation,
    val pronunciation2: Pronunciation,
    val imageUrl: String
) : Parcelable

@Parcelize
data class Pronunciation(
    val description: String,
    val soundUrl: String
) : Parcelable