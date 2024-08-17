package com.su.mmovie.data

import com.su.mmovie.data.mapper.toMovie
import com.su.mmovie.data.remote.MovieApi
import com.su.mmovie.domain.Movie
import com.su.mmovie.domain.MovieLists
import com.su.mmovie.domain.repository.MovieRepository
import com.su.mmovie.domain.util.Resource
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val api: MovieApi) : MovieRepository {
    override suspend fun getUpcomingMovie(): Resource<Movie> {
        return try {
            val response = api.getUpcomingMovie()
            println("response: ${response}")
            Resource.Success(response.toMovie())
        } catch (e: Throwable) {
            Resource.Error(message = e.localizedMessage)
        }
    }

    override suspend fun getMovieLists(): Resource<MovieLists> {
        return try {
            val upcoming = api.getUpcomingMovie()
            val newArrivals = api.getNowPlaying()
            val trending = api.getPopular()
            Resource.Success(MovieLists(
                upcoming = upcoming.toMovie().movieItems,
                newArrivals = newArrivals.toMovie().movieItems,
                trending = trending.toMovie().movieItems
            ))
        }catch (e:Throwable){
            Resource.Error(message = e.localizedMessage)
        }
    }
}