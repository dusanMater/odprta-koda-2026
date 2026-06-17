package com.example.domacanaloga_5_kalkulator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.ceil

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(top = 80.dp, start = 32.dp, end = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Calculate Tip",
                    fontSize = 24.sp,
                    color = Color.Black
                )

                Kalkulator()
            }
        }
    }
}

@Composable
fun Kalkulator() {
    var cena by remember { mutableStateOf("") }
    var procent by remember { mutableStateOf("") }
    var zaokrozi by remember { mutableStateOf(false) }
    val znesek = cena.toDoubleOrNull() ?: 0.0
    val tip = procent.toDoubleOrNull() ?: 0.0
    var napitnina1 = znesek * (tip / 100.0)
    if (zaokrozi) {
        napitnina1 = ceil(napitnina1)
    }
    val izpis = String.format("$%.2f", napitnina1)

    VnosVrstica(
        vrednost = cena,
        onVrednostChange = { cena = it },
        label = "Cost of Service",
        modifier = Modifier.fillMaxWidth()
    )
    VnosVrstica(
        vrednost = procent,
        onVrednostChange = { procent = it },
        label = "Tip (%)",
        modifier = Modifier.fillMaxWidth()
    )
    VrsticaZaokrozi(
        prikazano = zaokrozi,
        onPrikazanoChange = { zaokrozi = it },
        modifier = Modifier.fillMaxWidth()
    )
    VrsticaRezultat(
        rezultat = izpis,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun VnosVrstica(
    vrednost: String,
    onVrednostChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier
) {
    TextField(
        value = vrednost,
        onValueChange = onVrednostChange,
        label = { Text(text = label) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = modifier,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color(0xFFE2E2E2),
            focusedContainerColor = Color(0xFFD6D6D6)
        )
    )
}
@Composable
fun VrsticaZaokrozi(
    prikazano: Boolean,
    onPrikazanoChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Round up tip?", fontSize = 16.sp, color = Color.Black)
        Switch(
            checked = prikazano,
            onCheckedChange = onPrikazanoChange
        )
    }
}
@Composable
fun VrsticaRezultat(
    rezultat: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.padding(top = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Tip Amount: $rezultat",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}