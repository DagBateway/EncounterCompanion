package com.albertocamillo.encountercompanion.data

import com.squareup.moshi.Json

data class MonsterIndex (
    @field:Json(name = "index") val index: String,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "url") val url: String
)