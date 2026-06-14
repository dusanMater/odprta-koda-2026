package com.example.domacanaloga_6_9

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.White
            ) {
                postavitev()
            }
        }
    }
}

@Composable
fun postavitev() {
    var znesekVnos by remember { mutableStateOf("") }
    var napitninaVnos by remember { mutableStateOf("") }
    var zaokroziNavzgor by remember { mutableStateOf(false) }

    val znesek = znesekVnos.toDoubleOrNull() ?: 0.0
    val napitninaOdstotek = napitninaVnos.toDoubleOrNull() ?: 0.0

    val napitnina = izracunajNapitnino(znesek, napitninaOdstotek, zaokroziNavzgor)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Calculate Tip",
            fontSize = 24.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp).align(Alignment.Start)
        )

        Urejanje(
            oznaka = "Cost of Service",
            vrednost = znesekVnos,
            obSpremembiVrednosti = { znesekVnos = it },
            modifikator = Modifier.fillMaxWidth().padding(bottom = 32.dp)
        )

        Urejanje(
            oznaka = "Tip (%)",
            vrednost = napitninaVnos,
            obSpremembiVrednosti = { napitninaVnos = it },
            modifikator = Modifier.fillMaxWidth().padding(bottom = 32.dp)
        )

        Zaokrozi(
            zaokrožiNavzgor = zaokroziNavzgor,
            obSpremembiZaokrožiNavzgor = { zaokroziNavzgor = it },
            modifikator = Modifier.fillMaxWidth().padding(bottom = 32.dp)
        )

        Text(
            text = "Tip Amount: $napitnina",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

@Composable
fun Urejanje(
    oznaka: String,
    vrednost: String,
    obSpremembiVrednosti: (String) -> Unit,
    modifikator: Modifier = Modifier
) {
    TextField(
        value = vrednost,
        onValueChange = obSpremembiVrednosti,
        label = { Text(text = oznaka) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = modifikator
    )
}

@Composable
fun Zaokrozi(
    zaokrožiNavzgor: Boolean,
    obSpremembiZaokrožiNavzgor: (Boolean) -> Unit,
    modifikator: Modifier = Modifier
) {
    Row(
        modifier = modifikator,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Round up tip?", fontSize = 16.sp, color = Color.Black)
        Switch(
            checked = zaokrožiNavzgor,
            onCheckedChange = obSpremembiZaokrožiNavzgor
        )
    }
}

fun izracunajNapitnino(znesek: Double, napitninaOdstotek: Double, zaokrožiNavzgor: Boolean): String {
    var napitnina = znesek * (napitninaOdstotek / 100.0)
    if (zaokrožiNavzgor) {
        napitnina = ceil(napitnina)
    }
    return NumberFormat.getCurrencyInstance().format(napitnina)
}