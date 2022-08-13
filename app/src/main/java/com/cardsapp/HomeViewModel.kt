package com.cardsapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    private val _foldersLiveData = MutableLiveData<List<FolderInfo>>(emptyList())
    val foldersLiveData: LiveData<List<FolderInfo>> = _foldersLiveData

    private val _lastModules = MutableLiveData<List<ModuleInfo>>(emptyList())
    val lastModules: LiveData<List<ModuleInfo>> = _lastModules

    fun getFoldersInfoList() {
        _foldersLiveData.value = (1..10).map { FolderInfo(name = "Folder $it", size = 5) }
    }

    fun getLastModulesInfoList(count: Int) {
        _lastModules.value = (1..count).map { ModuleInfo(name = "Module $it", size = 5) }
    }
}