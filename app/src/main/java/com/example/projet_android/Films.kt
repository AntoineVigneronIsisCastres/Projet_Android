package com.example.projet_android

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import coil.compose.AsyncImage

sealed class BottomNavBarFilms(val route: String) {
    object Films : BottomNavBarFilms("Films")
    object Profile : BottomNavBarFilms("Profile")
}

@Composable
fun ScreenFilms(windowClass: WindowSizeClass, nav: NavHostController, viewmodel : MainViewModel) {
    Box() {
        when (windowClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> {
                Column(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    HeadVerticalFilms(nav, viewmodel)
                }
            }
            else -> {
                Row(
                    Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    HeadHorizontalFilms(nav, viewmodel)
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HeadVerticalFilms(nav: NavController, viewmodel : MainViewModel) {

    val movies by viewmodel.movies.collectAsState();

    viewmodel.getMovies()
    Box(modifier = Modifier.padding(30.dp)) {
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(movies) {
                movie -> FilmThumbnail(movie)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HeadHorizontalFilms(nav: NavController, viewmodel : MainViewModel) {
    val movies by viewmodel.movies.collectAsState();

    viewmodel.getMovies()
    Box(modifier = Modifier.padding(30.dp)) {
        LazyVerticalGrid(columns = GridCells.Fixed(3), modifier = Modifier.align(Alignment.Center)) {
            items(movies) {
                    movie -> FilmThumbnail(movie)
            }
        }
    }
}

@Composable
fun FilmThumbnail(movie : TmdbMovie){
    Card(modifier = Modifier.padding(5.dp), elevation = 10.dp) {
        Column(modifier = Modifier.padding(15.dp)) {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w400" + movie.poster_path,
                contentDescription = "Ma super image"
            )
            Text(text = movie.original_title, modifier = Modifier.padding(top = 5.dp))
            Text(text = movie.release_date, modifier = Modifier.padding(top = 3.dp), color = Color.Gray)
        }
    }
}


