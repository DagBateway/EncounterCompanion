package com.albertocamillo.encountercompanion.data

import com.albertocamillo.encountercompanion.network.ApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val encounterCompanionRepository: EncounterCompanionRepository
}

class DefaultAppContainer : AppContainer {
    private val baseUrl = "https://www.dnd5eapi.co/api/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    override val encounterCompanionRepository: EncounterCompanionRepository by lazy{
        NetworkEncounterCompanionRepository(retrofitService)
    }
}