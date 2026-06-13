package com.example.a10_artspaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.a10_artspaceapp.ui.theme._10ArtSpaceAppTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Galerija()
        }
    }
}

@Composable
fun Galerija() {

    var indeks by remember {
        mutableStateOf(0)
    }

    val slike = listOf(
        R.drawable.jazbec,
        R.drawable.rim,
        R.drawable.zahod
    )

    val naslovi = listOf(
        "Jazbec",
        "Rim",
        "Sončni zahod"
    )

    val avtorji = listOf(
        "Kat Kuan (2017)",
        "Jane Doe (2020)",
        "John Smith (2023)"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Slika(
            slika = slike[indeks]
        )

        Spacer(
            modifier = Modifier.height(32.dp)
        )

        Opis(
            naslov = naslovi[indeks],
            avtor = avtorji[indeks]
        )

        Spacer(
            modifier = Modifier.height(32.dp)
        )

        Gumbi(
            previous = {
                if (indeks > 0) {
                    indeks--
                }
            },

            next = {
                if (indeks < slike.size - 1) {
                    indeks++
                }
            }
        )
    }
}


@Composable
fun Slika(slika: Int) {

    Box(
        modifier = Modifier
            .background(Color.White)
            .padding(16.dp)
    ) {

        Image(
            painter = painterResource(slika),
            contentDescription = null,
            modifier = Modifier.size(300.dp)
        )
    }
}


@Composable
fun Opis(
    naslov: String,
    avtor: String
) {

    Column(
        modifier = Modifier
            .background(Color.LightGray)
            .padding(16.dp)
    ) {

        Text(
            text = naslov,
            fontSize = 28.sp
        )

        Text(
            text = avtor
        )
    }
}


@Composable
fun Gumbi(
    previous: () -> Unit,
    next: () -> Unit
) {

    Row(
        horizontalArrangement = Arrangement.spacedBy(32.dp)
    ) {

        Button(
            onClick = previous
        ) {
            Text("Previous")
        }

        Button(
            onClick = next
        ) {
            Text("Next")
        }
    }
}