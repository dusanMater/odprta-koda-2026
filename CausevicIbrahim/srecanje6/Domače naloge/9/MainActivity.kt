package com.example.domacanaloga_6_9

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
            Box(
                modifier = Modifier.fillMaxSize().background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(20.dp)
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

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        VnosVrstica(
            vrednost = cena,
            onVrednostChange = { cena = it },
            label = "Cost of Service",
            modifier = Modifier.width(250.dp)
        )
        VnosVrstica(
            vrednost = procent,
            onVrednostChange = { procent = it },
            label = "Tip (%)",
            modifier = Modifier.width(250.dp)
        )
        VrsticaZaokrozi(
            prikazano = zaokrozi,
            onPrikazanoChange = { zaokrozi = it }
        )
        VrsticaRezultat(
            rezultat = izpis
        )
    }
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
        Text(text = "Round up tip?", fontSize = 20.sp, color = Color.Black)
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
        modifier = modifier.padding(top = 20.dp),
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