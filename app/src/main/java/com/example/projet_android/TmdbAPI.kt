package com.example.projet_android

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbAPI {

    @GET("trending/movie/week")
    suspend fun lastmovies(@Query("api_key") api_key: String): TmdbMovieResult

    @GET("/3/search/movie")
    suspend fun searchmovie(@Query("query") searchword: String,@Query("api_key") apikey: String, @Query("language")language: String): TmdbMovieResult

    @GET("tv/popular")
    suspend fun lastseries(@Query("api_key") api_key: String): TmdbSeriesResult

    @GET("/3/search/tv")
    suspend fun searchseries(@Query("query") searchword: String,@Query("api_key") apikey: String, @Query("language")language: String): TmdbSeriesResult

    @GET("trending/person/week")
    suspend fun lastpeople(@Query("api_key") api_key: String): TmdbPersonneResult

    @GET("/3/search/person")
    suspend fun searchpeople(@Query("query") searchword: String,@Query("api_key") apikey: String, @Query("language")language: String): TmdbPersonneResult

    @GET("/3/movie/{id}")
    suspend fun getmovie(@Path("id") id: String, @Query("api_key") apikey: String, @Query("language")language: String): MovieDetail

    @GET("/3/tv/{id}")
    suspend fun getseries(@Path("id") id: String, @Query("api_key") apikey: String, @Query("language")language: String): SeriesDetail
}