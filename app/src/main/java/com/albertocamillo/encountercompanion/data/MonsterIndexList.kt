package com.albertocamillo.encountercompanion.data

import kotlinx.serialization.Serializable

@Serializable
data class MonsterIndexList (
    val count: Int,
    val results: List<MonsterIndex>
)