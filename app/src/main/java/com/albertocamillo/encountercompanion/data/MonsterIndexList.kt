package com.albertocamillo.encountercompanion.data

import com.squareup.moshi.Json

data class MonsterIndexList (
    @field:Json(name = "count") val count: Int,
    @field:Json(name = "results")val results: List<MonsterIndex>
)