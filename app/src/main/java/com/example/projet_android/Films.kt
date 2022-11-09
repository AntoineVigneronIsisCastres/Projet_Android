package com.example.projet_android

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
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
                    HeadVerticalFilms(nav, windowClass, viewmodel)
                }
            }
            else -> {
                Row(
                    Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    HeadHorizontalFilms(nav, windowClass, viewmodel)
                }
            }
        }
    }
}

sealed class Screen(var title: String, var icon:Int, var route: String) {
    object Films : Screen("Films", R.drawable.clap, "Films")
    object Series : Screen("Series", R.drawable.tv, "Series")
    object People : Screen("People", R.drawable.people, "Personnes")
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun HeadVerticalFilms(nav: NavController, windowClass : WindowSizeClass, viewmodel : MainViewModel) {

    val movies by viewmodel.movies.collectAsState()
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
                    viewmodel.searchMovies(it)
                },
                onSearchTriggered = {
                    viewmodel.updateSearchWidgetState(newValue = MainViewModel.SearchWidgetState.OPENED)
                }
            )
        },
        content = {
                LazyVerticalGrid(cells = GridCells.Fixed(2)) {
                    items(movies) {
                            movie -> FilmThumbnail(movie, nav)
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
fun HeadHorizontalFilms(nav: NavController, windowClass : WindowSizeClass, viewmodel : MainViewModel) {
    val movies by viewmodel.movies.collectAsState()

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
                    viewmodel.searchMovies(it)
                },
                onSearchTriggered = {
                    viewmodel.updateSearchWidgetState(newValue = MainViewModel.SearchWidgetState.OPENED)
                }
            )
        },
        content = {
            LazyVerticalGrid(cells = GridCells.Fixed(3)) {
                items(movies) {
                        movie -> FilmThumbnail(movie, nav)
                }
            }
        },
        bottomBar = {
            BottomNavBar(nav)
        }
    )
}

@Composable
fun FilmThumbnail(movie : TmdbMovie, nav: NavController){
    Log.d("test", movie.id)
    Card(modifier = Modifier.padding(5.dp).clickable {nav.navigate("Film" + "/${movie.id}")}, elevation = 10.dp) {
        Column(modifier = Modifier.padding(15.dp)) {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w400" + movie.poster_path,
                contentDescription = "Ma super image"
            )
            Text(text = movie.title, modifier = Modifier.padding(top = 5.dp))
            Text(
                text = movie.release_date,
                modifier = Modifier.padding(top = 3.dp),
                color = Color.Gray
            )
        }
    }
}

@Composable
fun BottomNavBar(nav: NavController){
    val items = listOf(
        Screen.Films,
        Screen.Series,
        Screen.People,
    )
    BottomNavigation(backgroundColor = MaterialTheme.colors.primary) {
        val navBackStackEntry by nav.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        items.forEach { screen ->
            BottomNavigationItem(
                icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
                label = { Text((screen.title)) },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.White,
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    nav.navigate(screen.route) {
                        nav.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = false
                    }
                }
            )
        }
    }
}





