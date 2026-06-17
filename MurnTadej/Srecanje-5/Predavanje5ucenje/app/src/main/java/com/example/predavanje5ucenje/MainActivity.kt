package com.example.predavanje5ucenje

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.predavanje5ucenje.ui.theme.Predavanje5ucenjeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KalkulatorPorabe()
        }
    }
}

// FUNKCIJA 4 (glavna): drži STANJE in sestavi vse vrstice
@Composable
fun KalkulatorPorabe(modifier: Modifier = Modifier) {
    var kilometri by remember { mutableStateOf("") }
    var litri by remember { mutableStateOf("") }

    val poraba = izracunajPorabo(kilometri, litri)   // izračun

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Spacer(Modifier.height(30.dp))


        Naslov("Kalkulator porabe goriva", Modifier.padding(top = 40.dp))

        VnosnaVrstica(
            label = "Prevoženi kilometri",
            vrednost = kilometri,
            onVrednostChange = { kilometri = it }
        )

        VnosnaVrstica(
            label = "Porabljeno gorivo (l)",
            vrednost = litri,
            onVrednostChange = { litri = it }
        )

        Rezultat(poraba)
    }
}

// FUNKCIJA 1: naslovna vrstica
@Composable
fun Naslov(besedilo: String, modifier: Modifier = Modifier) {
    Text(
        text = besedilo,
        fontSize = 40.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    )
}

// FUNKCIJA 2: vnosna vrstica — UPORABLJENA 2× z različnimi parametri
@Composable
fun VnosnaVrstica(
    label: String,
    vrednost: String,
    onVrednostChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = vrednost,
        onValueChange = onVrednostChange,
        label = { Text(label) },
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    )
}

// FUNKCIJA 3: vrstica z rezultatom
@Composable
fun Rezultat(poraba: Double, modifier: Modifier = Modifier) {
    Text(
        text = "Poraba: %.1f l/100km".format(poraba),
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    )
}

// navadna (NE-Composable) funkcija za izračun
fun izracunajPorabo(kilometriText: String, litriText: String): Double {
    val km = kilometriText.toDoubleOrNull() ?: 0.0
    val l = litriText.toDoubleOrNull() ?: 0.0
    if (km == 0.0) return 0.0          // brez deljenja z 0
    return (l / km) * 100
}