package com.example.jedilnilist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JedilniList()
        }
    }
}

// Barve palete (seaside vibe: krem ozadje + dve modri za zebro)
private val barvaOzadja = Color(0xFFFAF6EE)
private val barvaZebraA = Color(0xFFDCEAF2)
private val barvaZebraB = Color(0xFFB8D4E3)
private val barvaNaslova = Color(0xFF0B3954)
private val barvaImena = Color(0xFF264653)
private val barvaCene = Color(0xFF2A9D8F)

@Composable
fun JedilniList() {
    // Seznam jedi: (ime, cena)
    val jedi = listOf(
        "Burger" to "9,50 €",
        "Hot Dog" to "6,50 €",
        "Krompirček" to "4,50 €",
        "Sladoled" to "3,80 €",
        "Limonada" to "4,20 €",
        "Pivo" to "4,80 €"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(barvaOzadja)
            .padding(start = 24.dp, end = 24.dp, top = 200.dp, bottom = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Naslov (centriran)
        Text(
            text = "Jedilni list",
            color = barvaNaslova,
            fontSize = 80.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Vrstice z izmeničnim ozadjem (zebra vzorec - dodatek)
        jedi.forEachIndexed { index, (ime, cena) ->
            VrsticaJedi(
                ime = ime,
                cena = cena,
                ozadje = if (index % 2 == 0) barvaZebraA else barvaZebraB
            )
        }
    }
}

@Composable
private fun VrsticaJedi(ime: String, cena: String, ozadje: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(ozadje)
            .padding(horizontal = 12.dp, vertical = 14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = ime,
            color = barvaImena,
            fontSize = 50.sp
        )
        Text(
            text = cena,
            color = barvaCene,
            fontSize = 50.sp
        )
    }
}
