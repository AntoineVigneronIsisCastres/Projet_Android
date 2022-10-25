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

data class TmdbSeriesResult(
    val page: Int,
    val results: List<TmdbSeries>,
    val total_pages: Int,
    val total_results: Int
)

data class TmdbSeries(
    val adult: Boolean,
    val backdrop_path: String,
    val first_air_date: String,
    val genre_ids: List<Int>,
    val id: Int,
    val media_type: String,
    val name: String,
    val origin_country: List<String>,
    val original_language: String,
    val original_name: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val vote_average: Double,
    val vote_count: Int
)