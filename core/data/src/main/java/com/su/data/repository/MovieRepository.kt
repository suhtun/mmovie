package com.su.data.repository

import com.su.common.Resource
import com.su.model.Movie
import com.su.model.MovieLists

interface MovieRepository {

    suspend fun getUpcomingMovie(): Resource<com.su.model.Movie>

    suspend fun getMovieLists(): Resource<com.su.model.MovieLists>

//    suspend fun getNowPlaying(): Resource<Movie>
//
//    suspend fun getPopular(): Resource<Movie>
}