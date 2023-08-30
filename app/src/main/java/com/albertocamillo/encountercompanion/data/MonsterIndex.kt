package com.albertocamillo.encountercompanion.data

import kotlinx.serialization.Serializable

@Serializable
data class MonsterIndex (
    val index: String,
    val name: String,
    val url: String
)