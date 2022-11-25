package com.cardsapp.domain

import java.time.ZonedDateTime
import java.util.*

data class FolderInfo(
    val name: String,
    val creationDateTime: ZonedDateTime = ZonedDateTime.now(),
    val size: Int = 0
)