package com.su.mmovie.data.remote

import retrofit2.http.GET
import retrofit2.http.Header

interface MovieApi {

    @GET("movie/upcoming?language=en-US&page=1")
    suspend fun getUpcomingMovie(): MovieDto

    @GET("movie/now_playing?language=en-US&page=1")
    suspend fun getNowPlaying(): MovieDto

    @GET("movie/popular?language=en-US&page=1")
    suspend fun getPopular(): MovieDto
}