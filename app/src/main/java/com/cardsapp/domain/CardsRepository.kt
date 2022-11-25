package com.cardsapp.domain

import kotlinx.coroutines.flow.Flow


interface CardsRepository {

    fun getFoldersInfo(): FlowResult<List<FolderWithSize>>

    fun addFolder(folder: Folder): FlowResult<None>

    fun editFolder(oldName: String, newName: String): FlowResult<None>

    fun deleteFolders(folders: List<Folder>): FlowResult<None>


    fun addModule(name: String): FlowResult<None>

    fun deleteModule(name: String): FlowResult<None>

    fun moveToFolder(moduleName: String, folderName: String): FlowResult<None>

    fun removeFromFolder(moduleName: String): FlowResult<None>

    fun getModuleInfo(): FlowResult<List<ModuleInfo>>

}
