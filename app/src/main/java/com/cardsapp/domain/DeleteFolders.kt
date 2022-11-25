package com.cardsapp.domain

import javax.inject.Inject

class DeleteFolders @Inject constructor(
    private val cardsRepository: CardsRepository
) {
    operator fun invoke(folders: List<Folder>) = cardsRepository.deleteFolders(folders)
}