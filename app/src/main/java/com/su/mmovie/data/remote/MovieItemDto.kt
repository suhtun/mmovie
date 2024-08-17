package com.su.mmovie.data.remote

import com.squareup.moshi.Json

data class MovieItemDto(
    val adult: Boolean,
    @field:Json(name = "backdrop_path")
    val backdropPath: String,
    @field:Json(name = "genre_ids")
    val genreIds: List<Int>,
    val id: Int,
    @field:Json(name = "original_language")
    val originalLanguage: String,
    @field:Json(name = "original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @field:Json(name = "poster_path")
    val posterPath: String,
    @field:Json(name = "release_date")
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    @field:Json(name = "vote_average")
    val voteAverage: Double,
    @field:Json(name = "vote_count")
    val voteCount: Int
)