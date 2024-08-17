package com.su.mmovie.data.mapper

import com.su.mmovie.data.remote.MovieDto
import com.su.mmovie.data.remote.MovieItemDto
import com.su.mmovie.domain.Movie
import com.su.mmovie.domain.MovieItem


fun MovieDto.toMovie(): Movie {
    return Movie(movieItems = this.movieItems.map { it.toMovieItem() })
}

fun MovieItemDto.toMovieItem(): MovieItem {
    return MovieItem(
        overview = this.overview,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        title = this.title,
        voteAverage = this.voteAverage,
    )
}