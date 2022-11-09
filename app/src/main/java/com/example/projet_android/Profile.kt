package com.example.projet_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.projet_android.ui.theme.Projet_AndroidTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewmodel : MainViewModel by viewModels()
        setContent {
            val windowSizeClass = calculateWindowSizeClass(this)
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "Profile") {
                    composable("Profile") { Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                        ScreenProfile(windowSizeClass, navController)
                    }
                }
                    composable("Films") {Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                        ScreenFilms(windowSizeClass, navController, viewmodel)
                    }
                }
                    composable("Series") {Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                        ScreenSeries(windowSizeClass, navController, viewmodel)
                    }
                }
                    composable("Personnes") {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colors.background
                        ) {
                            ScreenPersonnes(windowSizeClass, navController, viewmodel)
                        }
                    }
                composable("Film" + "/{id}"){ NavBackStack ->
                    val id = NavBackStack.arguments?.getString("id")
                    if (id != null) {
                        ScreenFilmDetail(windowSizeClass, navController, viewmodel, id.toString())
                    }
                }

        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Projet_AndroidTheme {
        Greeting("Android")
    }
}

@Composable
fun HeadVerticalProfile(nav: NavController) {

    Box(modifier = Modifier.padding(30.dp)) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            MonImage()
            NomPrenom()
            AboutSection()
            Links()
            StartButton(nav)
        }
    }
}

@Composable
fun HeadHorizontalProfile(nav: NavController) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MonImage()
            NomPrenom()
            AboutSection()
        }

    }
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Links()
            StartButton(nav)
        }
    }
}

@Composable
fun MonImage() {
    Image(
        painterResource(id = R.mipmap.iledesmorts_foreground),
        contentDescription = "Antoine Vigneron",
        modifier = Modifier
            .size(200.dp)
            .clip(CircleShape)
            .border(2.dp, Color.Black, CircleShape)
            .fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun NomPrenom(){
    Text(
        text = "Antoine Vigneron",
        textAlign = TextAlign.Center,
        fontSize = 30.sp
    )
}

@Composable
fun AboutSection() {
    Text(
        text = "Étudiant en école d'ingénieur informatique e-santé",
        textAlign = TextAlign.Center,
        fontSize = 15.sp
    )
    Text(
        text = "École d'ingénieurs ISIS",
        textAlign = TextAlign.Center,
        fontSize = 15.sp,
        fontStyle = FontStyle.Italic
    )
}

@Composable
fun Links(){
    Spacer(modifier = Modifier.size(30.dp))
    Row(
    ) {
        Icon(
            imageVector = Icons.Filled.MailOutline,
            contentDescription = "Linkedin2",
        )

        Text(
            text = "antoine.vigneron@etud.univ-jfc.fr",
            fontSize = 13.sp)
    }
    Row(
    ) {
        Image(
            painterResource(id = R.mipmap.linkedin_foreground),
            contentDescription = "Linkedin",
            modifier = Modifier.size(20.dp)
        )
        Text(
            text = "https://www.linkedin.com/in/antoine-vigneron-8010621b9/",
            fontSize = 13.sp,
            textAlign = TextAlign.Center)
    }
}


@Composable
fun StartButton(nav: NavController){
    Spacer(modifier = Modifier.size(50.dp))
    Button(
        onClick = { nav.navigate("Films") }) {
        Text(text = "Démarrer")
    }
}

@Composable
fun ScreenProfile(windowClass: WindowSizeClass, nav: NavController) {
    Box() {
        when (windowClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> {
                Column(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    HeadVerticalProfile(nav)
                }
            }
            else -> {
                Row(
                    Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    HeadHorizontalProfile(nav)
                }
            }
        }
    }
}
}