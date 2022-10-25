package com.example.projet_android

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage

@Composable
fun ScreenSeries(windowClass: WindowSizeClass, nav: NavHostController, viewmodel : MainViewModel) {

    Box() {
        when (windowClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> {
                Column(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    HeadVerticalSeries(nav, windowClass, viewmodel)
                }
            }
            else -> {
                Row(
                    Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    HeadHorizontalSeries(nav, windowClass, viewmodel)
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun HeadVerticalSeries(nav: NavController, windowClass : WindowSizeClass, viewmodel : MainViewModel) {

    val series by viewmodel.series.collectAsState()
    viewmodel.getSeries()

    val searchWidgetState by viewmodel.searchWidgetState
    val searchTextState by viewmodel.searchTextState

    Scaffold(
        topBar = {
            TopSearchBar(
                searchWidgetState = searchWidgetState,
                searchTextState = searchTextState,
                onTextChange = {
                    viewmodel.updateSearchTextState(newValue = it)
                },
                onCloseClicked = {
                    viewmodel.updateSearchWidgetState(newValue = MainViewModel.SearchWidgetState.CLOSED)
                },
                onSearchClicked = {
                    Log.d("Searched Text", it)
                    viewmodel.searchSeries(it)
                },
                onSearchTriggered = {
                    viewmodel.updateSearchWidgetState(newValue = MainViewModel.SearchWidgetState.OPENED)
                }
            )
        },
        content = {
            LazyVerticalGrid(cells = GridCells.Fixed(2)) {
                items(series) {
                        serie -> SeriesThumbnail(serie)
                }
            }
        },
        bottomBar = {
            BottomNavBar(nav)
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HeadHorizontalSeries(nav: NavController, windowClass : WindowSizeClass, viewmodel : MainViewModel) {
    val series by viewmodel.series.collectAsState()
    viewmodel.getSeries()

    val searchWidgetState by viewmodel.searchWidgetState
    val searchTextState by viewmodel.searchTextState

    Scaffold(
        topBar = {
            TopSearchBar(
                searchWidgetState = searchWidgetState,
                searchTextState = searchTextState,
                onTextChange = {
                    viewmodel.updateSearchTextState(newValue = it)
                },
                onCloseClicked = {
                    viewmodel.updateSearchWidgetState(newValue = MainViewModel.SearchWidgetState.CLOSED)
                },
                onSearchClicked = {
                    Log.d("Searched Text", it)
                    viewmodel.searchSeries(it)
                },
                onSearchTriggered = {
                    viewmodel.updateSearchWidgetState(newValue = MainViewModel.SearchWidgetState.OPENED)
                }
            )
        },
        content = {
            LazyVerticalGrid(cells = GridCells.Fixed(3)) {
                items(series) {
                        serie -> SeriesThumbnail(serie)
                }
            }
        },
        bottomBar = {
            BottomNavBar(nav)
        }
    )
}

@Composable
fun SeriesThumbnail(serie : TmdbSeries){
    Card(modifier = Modifier.padding(5.dp), elevation = 10.dp) {
        Column(modifier = Modifier.padding(15.dp)) {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w400" + serie.poster_path,
                contentDescription = "Ma super image"
            )
            Text(text = serie.original_name, modifier = Modifier.padding(top = 5.dp))
            Text(text = serie.first_air_date, modifier = Modifier.padding(top = 3.dp), color = Color.Gray)
        }
    }
}