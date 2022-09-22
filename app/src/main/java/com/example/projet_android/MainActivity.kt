package com.example.projet_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projet_android.ui.theme.Projet_AndroidTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val windowSizeClass = calculateWindowSizeClass(this)

            Projet_AndroidTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Screen(windowSizeClass)
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
fun HeadVertical() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        MonImage()
        NomPrenom()
        AboutSection()
        Links()
        StartButton()
    }
}

@Composable
fun HeadHorizontal() {
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
            StartButton()
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
fun StartButton(){
    Spacer(modifier = Modifier.size(50.dp))
    Button(
        onClick = { /*TODO*/ }
    ) {
        Text(text = "Démarrer")
    }
}

@Composable
fun Screen(windowClass: WindowSizeClass) {
    Box() {
        when (windowClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> {
                Column(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    HeadVertical()
                }
            }
            else -> {
                Row(
                    Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    HeadHorizontal()
                }
            }
        }
    }
}