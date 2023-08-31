package com.albertocamillo.encountercompanion.network

import com.albertocamillo.encountercompanion.data.MonsterDetails
import com.albertocamillo.encountercompanion.data.MonsterIndexList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface ApiService {
    @GET("api/monsters")
    suspend fun getMonsterIndexList(): MonsterIndexList

    @GET("api/monsters/{monsterName}")
    suspend fun getMonsterDetails(@Path("monsterName") monsterName: String): MonsterDetails
}

