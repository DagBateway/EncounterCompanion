package com.albertocamillo.encountercompanion.data

import com.albertocamillo.encountercompanion.network.ApiService

interface EncounterCompanionRepository {
    suspend fun getMonsterIndexList(): MonsterIndexList
    suspend fun getMonsterDetails(url: String): MonsterDetails
}

class NetworkEncounterCompanionRepository(private val apiService: ApiService): EncounterCompanionRepository{
    override suspend fun getMonsterIndexList(): MonsterIndexList = apiService.getMonsterIndexList()
    override suspend fun getMonsterDetails(url: String): MonsterDetails = apiService.getMonsterDetails(url)
}