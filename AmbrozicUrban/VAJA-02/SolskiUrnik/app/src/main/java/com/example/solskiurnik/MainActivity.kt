package com.example.solskiurnik

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SolskiUrnik()
        }
    }
}

// Paleta zaslona
private val barvaOzadja = Color(0xFF2E3B47)
private val barvaUre = Color(0xFFF5A623)
private val barvaPredmeta = Color.White

// Barve vrstic (dodatek: vsak predmet svoja barva)
private val barveVrstic = listOf(
    Color(0xFF3D5A7F), // muted modra
    Color(0xFF4A7A52), // muted zelena
    Color(0xFF6B4A8A), // muted vijolična
    Color(0xFF3D7A75), // muted turkizna
    Color(0xFF7A4D4A), // muted koralna
    Color(0xFF7A6B47)  // muted oliva
)

@Composable
fun SolskiUrnik() {
    // Seznam predmetov: (ura, predmet)
    val urnik = listOf(
        "08:00" to "Programiranje",
        "09:00" to "Baze podatkov",
        "10:00" to "Spletne strani",
        "11:00" to "Omrežja",
        "12:00" to "Management",
        "13:00" to "Oblačne storitve"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(barvaOzadja)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Naslov
        Text(
            text = "Moj urnik",
            color = Color.White,
            fontSize = 64.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.Monospace,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Vrstice z različnimi barvami (dodatek)
        urnik.forEachIndexed { index, (ura, predmet) ->
            VrsticaUrnika(
                ura = ura,
                predmet = predmet,
                ozadje = barveVrstic[index]
            )
            if (index < urnik.size - 1) {
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
private fun VrsticaUrnika(ura: String, predmet: String, ozadje: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(ozadje)
            .padding(horizontal = 20.dp, vertical = 18.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = ura,
            color = barvaUre,
            fontSize = 40.sp,
            fontFamily = FontFamily.Monospace
        )
        Text(
            text = predmet,
            color = barvaPredmeta,
            fontSize = 34.sp,
            fontFamily = FontFamily.Monospace
        )
    }
}
