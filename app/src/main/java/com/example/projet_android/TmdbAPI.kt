package com.example.projet_android

import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbAPI {

    @GET("trending/movie/week")
    suspend fun lastmovies(@Query("api_key") api_key: String): TmdbMovieResult
}