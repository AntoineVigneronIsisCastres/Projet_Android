package com.example.projet_android

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun ScreenFilmDetail(windowClass: WindowSizeClass, nav: NavHostController, viewmodel : MainViewModel, id: String) {

    Box() {
        when (windowClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> {
                Column(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    HeadVerticalFilmDetail(nav, windowClass, viewmodel, id)
                }
            }
            else -> {
                Row(
                    Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    //HeadHorizontalFilmDetail(nav, windowClass, viewmodel)
                }
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun HeadVerticalFilmDetail(nav: NavController, windowClass : WindowSizeClass, viewmodel : MainViewModel, id: String) {

    val movie by viewmodel.movie.collectAsState()
    Log.d("test", "OOOOOOOOOOOOOOOO"+id.toString())
    viewmodel.movieDetail(id.toString())
    val searchWidgetState by viewmodel.searchWidgetState
    val searchTextState by viewmodel.searchTextState
    Scaffold(
        content = {
            Box() {
                if (movie != null) {
                    LazyColumn(Modifier.padding(10.dp)) {
                        item {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = movie!!.title,
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.ExtraBold,
                                    modifier = Modifier.fillMaxWidth().padding(10.dp),
                                    style = MaterialTheme.typography.h4
                                )
                                AsyncImage(
                                    model = "https://image.tmdb.org/t/p/original" + movie!!.backdrop_path,
                                    contentDescription = "affiche",
                                    modifier = Modifier
                                        .fillMaxSize(0.95f)
                                        .padding(bottom = 16.dp),
                                    contentScale = ContentScale.Fit
                                )
                                Text(
                                    text = "Synopsis",
                                    style = MaterialTheme.typography.h4,
                                    modifier = Modifier.padding(bottom = 10.dp))
                                Box(modifier = Modifier.padding(bottom = 20.dp, start = 10.dp, end = 10.dp)) {
                                    Text(
                                        text = movie!!.overview,
                                        textAlign = TextAlign.Justify,
                                        modifier = Modifier.padding(bottom = 20.dp, start = 10.dp, end = 10.dp))
                                }
                                Row {
                                    AsyncImage(
                                        model = "https://image.tmdb.org/t/p/original" + movie!!.poster_path,
                                        contentDescription = "poster",
                                        modifier = Modifier
                                            .height(200.dp)
                                            .padding(10.dp, 10.dp),
                                        contentScale = ContentScale.Fit
                                    )
                                    Column (modifier = Modifier.padding(10.dp)){
                                        Text(
                                            text = "Date de sortie : " + movie!!.release_date,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                        Text(text = "Genre : ", fontWeight = FontWeight.SemiBold)
                                        for (genre in movie!!.genres) {
                                            Text(
                                                text = "- " + genre.name,
                                                fontStyle = FontStyle.Italic
                                            )
                                        }
                                    }
                                }

                            }
                            Text(text = "Casting", style = MaterialTheme.typography.h4)
                        }
                    }
                }
            }
        },
        bottomBar = {
            BottomNavBar(nav)
        }
    )
}
