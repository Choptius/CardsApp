package com.cardsapp.domain

import javax.inject.Inject

class GetFoldersWithSizeStream @Inject constructor(private val cardsRepository: CardsRepository) {
    operator fun invoke() = cardsRepository.getFoldersInfo()
}