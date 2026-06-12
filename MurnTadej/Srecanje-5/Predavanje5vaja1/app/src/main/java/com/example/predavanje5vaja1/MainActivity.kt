package com.example.predavanje5vaja1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.ceil

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KalkulatorNapitnine()
        }
    }
}


@Composable
fun KalkulatorNapitnine(modifier: Modifier = Modifier) {
    var cena by remember { mutableStateOf("") }
    var procent by remember { mutableStateOf("") }
    var zaokrozi by remember { mutableStateOf(false) }

    val napitnina = izracunajNapitnino(cena, procent, zaokrozi)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(45.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Naslov("Calculate Tip")
        VnosnaVrstica(
            label = "Cost of Service",
            vrednost = cena,
            onVrednostChange = { cena = it }
        )
        VnosnaVrstica(
            label = "Tip (%)",
            vrednost = procent,
            onVrednostChange = { procent = it }
        )
        ZaokroziVrstica(
            besedilo = "Round up tip?",
            izbrano = zaokrozi,
            onIzbranoChange = { zaokrozi = it }
        )
        Rezultat(napitnina)
    }
}


@Composable
fun Naslov(besedilo: String, modifier: Modifier = Modifier) {
    Text(
        text = besedilo,
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    )
}


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


@Composable
fun ZaokroziVrstica(
    besedilo: String,
    izbrano: Boolean,
    onIzbranoChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(besedilo)
        Switch(
            checked = izbrano,
            onCheckedChange = onIzbranoChange
        )
    }
}


@Composable
fun Rezultat(napitnina: Double, modifier: Modifier = Modifier) {
    Text(
        text = "Tip Amount: \$%.2f".format(napitnina),
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    )
}


fun izracunajNapitnino(cenaText: String, procentText: String, zaokrozi: Boolean): Double {
    val cena = cenaText.toDoubleOrNull() ?: 0.0
    val procent = procentText.toDoubleOrNull() ?: 0.0

    var napitnina = cena * procent / 100
    if (zaokrozi) {
        napitnina = ceil(napitnina)
    }
    return napitnina
}