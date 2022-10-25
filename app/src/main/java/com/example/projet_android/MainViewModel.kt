package com.example.projet_android

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainViewModel : ViewModel() {
    val movies = MutableStateFlow<List<TmdbMovie>>(listOf())
    val series = MutableStateFlow<List<TmdbSeries>>(listOf())
    val api_key = "cef16095ad6f59a18088e4ccfe5bd29a"

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build();

    val service = retrofit.create(TmdbAPI::class.java)

    fun getMovies() {
        viewModelScope.launch {
            movies.value = service.lastmovies(api_key).results
        }
    }

    fun getSeries() {
        viewModelScope.launch {
            series.value = service.lastseries(api_key).results
        }
    }

    fun searchMovies(searchword: String) {
        viewModelScope.launch {
            movies.value = service.searchmovie(searchword,api_key,"fr").results
        }
    }

    fun searchSeries(searchword: String) {
        viewModelScope.launch {
            series.value = service.searchseries(searchword,api_key,"fr").results
        }
    }

    enum class SearchWidgetState {
        OPENED,
        CLOSED
    }

    private val _searchWidgetState: MutableState<SearchWidgetState> =
        mutableStateOf(value = SearchWidgetState.CLOSED)
    val searchWidgetState: State<SearchWidgetState> = _searchWidgetState

    private val _searchTextState: MutableState<String> =
        mutableStateOf(value = "")
    val searchTextState: State<String> = _searchTextState

    fun updateSearchWidgetState(newValue: SearchWidgetState) {
        _searchWidgetState.value = newValue
    }

    fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
    }
}