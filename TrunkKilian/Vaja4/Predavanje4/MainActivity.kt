package com.example.am04

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

data class Sadje(
    val ime: String,
    val starost: Int,
    val slika: Int
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Column(
                modifier = Modifier
                    .statusBarsPadding()
            ) {
                PrikazFotografij()
            }
        }
    }
}

@ComposablezFot
fun PrikazFotografij() {
    val sadje = listOf(
        Sadje("Banana", 7, R.drawable.banana),
        Sadje("Apple", 3, R.drawable.apple),
        Sadje("Orange", 5, R.drawable.orange)
    )

    Column {
        for (sadez in sadje) {
            Vrstica(
                ime = sadez.ime,
                starost = sadez.starost,
                slika = sadez.slika
            )
        }
    }
}

@Composable
fun Vrstica(
    ime: String,
    starost: Int,
    slika: Int
) {
    Row(
        verticalAlignment = Alignment.Top,
        modifier = Modifier.padding(bottom = 12.dp)
    ) {
        Image(
            painter = painterResource(id = slika),
            contentDescription = ime,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(169.dp)
                .height(169.dp)
        )
        Column(
            modifier = Modifier.padding(start = 16.dp)
        ) {
            Text("Ime: $ime")
            Text("Starost: $starost")
        }
    }
}
