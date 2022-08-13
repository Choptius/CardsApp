package com.cardsapp

interface ModulesHistoryRepository {

    fun getModulesHistory(elementsCount: Int): List<ModuleInfo>

    fun addToHistory(moduleName: String)

    fun deleteFromHistory(moduleName: String)


}