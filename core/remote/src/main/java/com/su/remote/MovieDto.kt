package com.su.remote

import com.squareup.moshi.Json


data class MovieDto(
    @field:Json(name = "results")
    val movieItems: List<MovieItemDto>
)