package com.albertocamillo.encountercompanion.data

import com.squareup.moshi.Json

data class MonsterDetails (
    @field:Json(name = "name") val name: String,
    @field:Json(name = "image") val image: String
)