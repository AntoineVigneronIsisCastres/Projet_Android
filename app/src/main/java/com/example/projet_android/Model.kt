package com.example.projet_android

data class TmdbMovieResult(
    var page: Int = 0,
    val results: List<TmdbMovie> = listOf(),
    val total_pages : Int = 0,
    val total_results : Int = 0
)

data class TmdbMovie(
    var overview: String = "",
    val release_date: String = "",
    val id: String = "",
    val title: String = "",
    val original_title: String = "",
    val backdrop_path: String? = "",
    val genre_ids: List<Int> = listOf(),
    val poster_path: String? = "")