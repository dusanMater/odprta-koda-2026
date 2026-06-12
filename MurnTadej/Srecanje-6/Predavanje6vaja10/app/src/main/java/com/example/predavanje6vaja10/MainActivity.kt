package com.example.predavanje6vaja10

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.predavanje6vaja10.ui.theme.Predavanje6vaja10Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpace()
        }
    }
}

@Composable
fun ArtSpace() {
    var trenutna by remember { mutableStateOf(1) }

    val slika = when (trenutna) {
        1 -> R.drawable.slika1
        2 -> R.drawable.slika2
        3 -> R.drawable.slika3
        4 -> R.drawable.slika4
        else -> R.drawable.slika5
    }
    val naslov = when (trenutna) {
        1 -> "Corvette C8 Stingray 2020"
        2 -> "Mercedes-AMG GT R 2017"
        3 -> "Toyota GT86 2016"
        4 -> "Ferrari 360 Modena 2000"
        else -> "Lamborghini Veneno 2014"
    }
    val avtor = when (trenutna) {
        1 -> "6.2L V8 - 502hp - 120.000€"
        2 -> "4.0 L V8 Biturbo - 585hp - 190.000€"
        3 -> "2.0 L 4cln - 200hp - 25.000€"
        4 -> "3.6 L V8 - 395hp - 150.000€"
        else -> "6.5 L V12 - 750hp - 4.000.000€"
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Slika(slika)
        Spacer(Modifier.height(32.dp))
        Podatki(naslov, avtor)
        Spacer(Modifier.height(32.dp))
        Gumbi(
            onPrejsnja = { trenutna = if (trenutna == 1) 5 else trenutna - 1 },
            onNaslednja = { trenutna = if (trenutna == 5) 1 else trenutna + 1 }
        )
    }
}

@Composable
fun Slika(resId: Int) {
    Image(
        painter = painterResource(resId),
        contentDescription = null,
        modifier = Modifier.size(300.dp)
    )
}

@Composable
fun Podatki(naslov: String, avtor: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(naslov, fontSize = 24.sp)
        Text(avtor, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun Gumbi(onPrejsnja: () -> Unit, onNaslednja: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(onClick = onPrejsnja) { Text("Prejšnja") }
        Button(onClick = onNaslednja) { Text("Naslednja") }
    }
}

