package com.cardsapp.data

import androidx.room.*
import com.cardsapp.domain.ModuleInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Dao
interface CardsDao {

    @Query("SELECT *, (SELECT count(*) FROM modules WHERE owner_folder_name = folder_name) AS size FROM folders")
    fun getFoldersInfo(): Flow<List<RoomFolderWithSize>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFolder(folderEntity: FolderEntity)

    @Delete
    fun deleteFolders(folderEntities: List<FolderEntity>)

    @Query("SELECT * FROM folders")
    fun getFolders(): Flow<List<FolderEntity>>

    @Query("SELECT COUNT() FROM modules WHERE owner_folder_name = :name")
    fun getFolderSize(name: String): Int

    @Query("SELECT COUNT() FROM folders WHERE folder_name = :name")
    fun folderCount(name: String): Int

    @Query("UPDATE modules SET owner_folder_name = :newName WHERE owner_folder_name = :oldName")
    fun updateFolderNameChanged(oldName: String, newName: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertModule(moduleEntity: ModuleEntity)

    @Query("SELECT * FROM modules")
    fun getModules(): Flow<List<ModuleEntity>>

    fun getModulesInfo(): Flow<List<ModuleInfo>> {
        return getModules().map { list ->
            list.map { it.toModuleInfo(size = 0, coverImageUri = "") }
        }
    }
}

fun ModuleEntity.toModuleInfo(size: Int, coverImageUri: String) = ModuleInfo(
    name = this.name,
    creationTime = this.creationTime,
    ownerFolderName = this.ownerFolderName,
    size = size,
    coverImageUri = coverImageUri
)