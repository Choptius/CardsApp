package com.cardsapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cardsapp.domain.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class FoldersViewModel @Inject constructor(
    private val getFoldersWithSizeStream: GetFoldersWithSizeStream,
    private val searchFolders: SearchFolders,
    private val deleteFolders: DeleteFolders
) : ViewModel() {

    private val _state = MutableStateFlow<State>(State.Loading)
    val state = _state.asStateFlow()

    val folders = getFoldersWithSizeStream().stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        Result.Loading()
    )

    init {
        val result = folders.value
        _state.value = when (result) {
            is Result.Loading -> State.Loading
            is Result.Error -> State.Error(result.failure)
            is Result.Success -> State.Default(result.data)
        }
    }

    private inline fun <T : State> handleSuccessResult(
        result: Result<List<FolderWithSize>>,
        transform: (Result.Success<List<FolderWithSize>>) -> T
    ) {
        _state.value = when (result) {
            is Result.Loading -> State.Loading
            is Result.Error -> State.Error(result.failure)
            is Result.Success -> transform(result)
        }
    }

    sealed interface State {
        object Loading : State

        object Empty : State

        class Error(val failure: Failure) : State

        abstract class Abstract(val folders: List<FolderWithSize>) : State

        class Default(folders: List<FolderWithSize>) : Abstract(folders)

        class SelectionMode(
            folders: List<FolderWithSize>,
            val selectedFolders: List<FolderWithSize>
        ) : Abstract(folders)

        class SearchMode(
            val foundFolders: List<FolderWithSize>
        ) : State

    }

    sealed interface UserAction {
        class SearchFolders(val searchQuery: String) : UserAction
        object CancelSearch : UserAction
        class FolderSelected(val folderWithSize: FolderWithSize) : UserAction
        object SelectionFinished : UserAction
    }

    sealed interface Event {
        class SearchResult(val foundFolders: List<FolderWithSize>) : Event
    }

}