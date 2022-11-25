package com.cardsapp.domain

import java.time.ZonedDateTime

data class Folder(
    val name: String,
    val creationTime: ZonedDateTime = ZonedDateTime.now()
)