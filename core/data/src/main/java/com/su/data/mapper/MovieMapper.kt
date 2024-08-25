package com.su.data.mapper

import com.su.model.Movie
import com.su.model.MovieItem


fun com.su.remote.MovieDto.toMovie(): com.su.model.Movie {
    return com.su.model.Movie(movieItems = this.movieItems.map { it.toMovieItem() })
}

fun com.su.remote.MovieItemDto.toMovieItem(): com.su.model.MovieItem {
    return com.su.model.MovieItem(
        overview = this.overview,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        title = this.title,
        voteAverage = this.voteAverage,
    )
}