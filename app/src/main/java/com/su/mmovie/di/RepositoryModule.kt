package com.su.mmovie.di

import com.su.mmovie.data.MovieRepositoryImpl
import com.su.mmovie.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {


    @Binds
    @Singleton
    abstract fun bindMovieRepository(repositoryImpl: MovieRepositoryImpl): MovieRepository
}