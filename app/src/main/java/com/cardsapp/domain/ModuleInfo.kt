package com.cardsapp.domain

import java.time.ZonedDateTime

data class ModuleInfo(
    val name: String,
    val creationTime: ZonedDateTime = ZonedDateTime.now(),
    val size: Int = 0,
    val coverImageUri: String = "",
    val ownerFolderName: String = ""
)