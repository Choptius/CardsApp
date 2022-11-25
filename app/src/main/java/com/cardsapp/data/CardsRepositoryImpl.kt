package com.cardsapp.data

import com.cardsapp.domain.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CardsRepositoryImpl @Inject constructor(
    private val folderDao: CardsDao
): CardsRepository {

    override fun getFoldersInfo() = folderDao.getFoldersInfo().map {
        Result.Success(it.map { it.toFolderWithSize() })
    }

    override fun addFolder(folder: Folder): FlowResult<None> = flow {
        emit(Result.Loading())
    }

    override fun editFolder(oldName: String, newName: String): FlowResult<None> {
        TODO("Not yet implemented")
    }

    override fun deleteFolders(folders: List<Folder>): FlowResult<None> {
        TODO("Not yet implemented")
    }
    override fun addModule(name: String): FlowResult<None> {
        TODO("Not yet implemented")
    }

    override fun deleteModule(name: String): FlowResult<None> {
        TODO("Not yet implemented")
    }

    override fun moveToFolder(moduleName: String, folderName: String): FlowResult<None> {
        TODO("Not yet implemented")
    }

    override fun removeFromFolder(moduleName: String): FlowResult<None> {
        TODO("Not yet implemented")
    }

    override fun getModuleInfo(): FlowResult<List<ModuleInfo>> {
        TODO("Not yet implemented")
    }

}