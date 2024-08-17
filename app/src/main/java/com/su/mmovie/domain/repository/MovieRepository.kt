package com.su.mmovie.domain.repository

import com.su.mmovie.domain.Movie
import com.su.mmovie.domain.MovieLists
import com.su.mmovie.domain.util.Resource

interface MovieRepository {

    suspend fun getUpcomingMovie(): Resource<Movie>

    suspend fun getMovieLists(): Resource<MovieLists>

//    suspend fun getNowPlaying(): Resource<Movie>
//
//    suspend fun getPopular(): Resource<Movie>
}