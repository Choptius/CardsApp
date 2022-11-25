package com.cardsapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cardsapp.domain.Folder
import java.time.ZonedDateTime

@Entity(tableName = "folders")
data class FolderEntity(
    @PrimaryKey
    @ColumnInfo(name = "folder_name")
    val name: String,

    @ColumnInfo(name = "creation_date_time")
    val creationDateTime: ZonedDateTime = ZonedDateTime.now()
)

fun FolderEntity.toFolder() = Folder(
    name = this.name,
    creationTime = this.creationDateTime,
)
