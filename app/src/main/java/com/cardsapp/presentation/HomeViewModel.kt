package com.cardsapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cardsapp.domain.FolderInfo
import com.cardsapp.domain.ModuleInfo
import java.time.ZonedDateTime

class HomeViewModel : ViewModel() {
    private val _foldersLiveData = MutableLiveData<List<FolderInfo>>(emptyList())
    val foldersLiveData: LiveData<List<FolderInfo>> = _foldersLiveData

    private val _lastModules = MutableLiveData<List<ModuleInfo>>(emptyList())
    val lastModules: LiveData<List<ModuleInfo>> = _lastModules

    init {
        getFoldersInfoList()
    }
    fun getFoldersInfoList() {
        _foldersLiveData.value = (1..10).map { FolderInfo(name = "Folder $it", size = 5) }
    }

    fun getLastModulesInfoList(count: Int) {
        _lastModules.value = (1..count).map { ModuleInfo(name = "Module $it", size = 5) }
    }
}