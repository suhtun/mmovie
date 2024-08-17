package com.su.mmovie.domain

data class MovieLists(
    val upcoming: List<MovieItem>,
    val newArrivals: List<MovieItem>,
    val trending: List<MovieItem>
)
