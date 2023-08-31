package com.albertocamillo.encountercompanion.data

import com.albertocamillo.encountercompanion.network.ApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import com.squareup.moshi.JsonReader
import com.squareup.moshi.Moshi
import retrofit2.converter.moshi.MoshiConverterFactory

interface AppContainer {
    val encounterCompanionRepository: EncounterCompanionRepository
}

class DefaultAppContainer : AppContainer {
    private val baseUrl = "https://www.dnd5eapi.co/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create().asLenient()).build()

    private val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    override val encounterCompanionRepository: EncounterCompanionRepository by lazy{
        NetworkEncounterCompanionRepository(retrofitService)
    }
}