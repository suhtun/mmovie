package com.su.remote.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton


const val TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxOWFkYTc5OTgwNWRmOTVmZTMxMTM0OGU4OWM1NjI2ZCIsIm5iZiI6MTcyMzgyNjY2Mi43NTc2NDcsInN1YiI6IjY2YmY3ZmQzODc3N2U0NzdhOTFmMzMyMSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.mn7WXbTyhOOBan6wj5Qwja-TFNymUbVqTNzxdtUSQl8"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideHeaderInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $TOKEN")
                .addHeader("Content-Type", "application/json")
                .build()
            chain.proceed(request)
        }
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(headerInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRestService(okHttpClient: OkHttpClient): com.su.remote.MovieApi {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }
}