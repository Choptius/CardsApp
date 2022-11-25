package com.cardsapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


data class Card(
    val id: Int,
    val moduleName: String = "",
    val wordOrSentence: String,
    val definedWord: String = "",
    val infinitiveOfDefinedWord: String = "",
    val definition: String,
    val contextExamples: List<String>,
    val pronunciations: List<Pronunciation>,
    val imageUrl: String = ""
)


data class Pronunciation(
    val description: String,
    val soundUrl: String
)