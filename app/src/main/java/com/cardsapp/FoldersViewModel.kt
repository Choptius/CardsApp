package com.cardsapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class FoldersViewModel : ViewModel() {

    private val _foldersInfoList = MutableLiveData<List<FolderInfo>>(emptyList())
    val foldersInfoList: LiveData<List<FolderInfo>> = _foldersInfoList

    private val list = List(1450) { FolderInfo(name = "Folder $it", size = 5) }


    fun getFoldersInfoList() {
        _foldersInfoList.value = list
    }

    fun searchFolders(query: String) {
        _foldersInfoList.value =
            list.filter { it.name.contains(query, true) }
    }
    
    fun deleteFolders(folders: List<FolderInfo>) {
        _foldersInfoList.value = list.minus(folders)
    }
}