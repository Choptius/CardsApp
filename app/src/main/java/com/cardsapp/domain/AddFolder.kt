package com.cardsapp.domain

class AddFolder(private val cardsRepository: CardsRepository) {
    operator fun invoke(folder: Folder) =  cardsRepository.addFolder(folder)
}