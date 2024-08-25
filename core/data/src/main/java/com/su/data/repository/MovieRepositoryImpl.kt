package com.su.data.repository

import com.su.data.mapper.toMovie
import com.su.remote.MovieApi
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val api: MovieApi) : MovieRepository {
    override suspend fun getUpcomingMovie(): com.su.common.Resource<com.su.model.Movie> {
        return try {
            val response = api.getUpcomingMovie()
            println("response: ${response}")
            com.su.common.Resource.Success(response.toMovie())
        } catch (e: Throwable) {
            com.su.common.Resource.Error(message = e.localizedMessage)
        }
    }

    override suspend fun getMovieLists(): com.su.common.Resource<com.su.model.MovieLists> {
        return try {
            val upcoming = api.getUpcomingMovie()
            val newArrivals = api.getNowPlaying()
            val trending = api.getPopular()
            com.su.common.Resource.Success(
                com.su.model.MovieLists(
                    upcoming = upcoming.toMovie().movieItems,
                    newArrivals = newArrivals.toMovie().movieItems,
                    trending = trending.toMovie().movieItems
                )
            )
        }catch (e:Throwable){
            com.su.common.Resource.Error(message = e.localizedMessage)
        }
    }
}