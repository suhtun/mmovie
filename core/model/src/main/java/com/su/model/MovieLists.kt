package com.su.model

data class MovieLists(
    val upcoming: List<MovieItem>,
    val newArrivals: List<MovieItem>,
    val trending: List<MovieItem>
)
