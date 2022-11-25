package com.cardsapp.domain

import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchFolders @Inject constructor(
    private val cardsRepository: CardsRepository
) {
    operator fun invoke(searchQuery: String): FlowResult<List<FolderWithSize>> {
        val flowResult = cardsRepository.getFoldersInfo()
        return flowResult.map { result ->
            result.mapSuccess { list ->
                list.filter { it.folder.name.contains(searchQuery, true)}
            }
        }
    }
}
