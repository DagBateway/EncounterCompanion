package com.albertocamillo.encountercompanion.data

import kotlinx.serialization.Serializable

@Serializable
data class MonsterDetails (
    val name: String,
    val image: String
)