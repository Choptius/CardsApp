package com.cardsapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.ZonedDateTime

@Entity(tableName = "modules")
data class ModuleEntity(
    @PrimaryKey
    @ColumnInfo(name = "module_name")
    val name: String,

    @ColumnInfo(name = "creation_time")
    val creationTime: ZonedDateTime = ZonedDateTime.now(),

    @ColumnInfo(name = "owner_folder_name")
    val ownerFolderName: String = ""
)
