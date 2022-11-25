package com.cardsapp.data

import androidx.room.Embedded
import com.cardsapp.domain.FolderInfo
import com.cardsapp.domain.FolderWithSize

data class RoomFolderWithSize(
    @Embedded
    val folderEntity: FolderEntity,
    val size: Int
)

fun RoomFolderWithSize.toFolderWithSize() = FolderWithSize(
    folder = this.folderEntity.toFolder(),
    size = this.size
)
