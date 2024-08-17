package com.su.mmovie.data.remote

import com.squareup.moshi.Json


data class MovieDto(
    @field:Json(name = "results")
    val movieItems: List<MovieItemDto>
)