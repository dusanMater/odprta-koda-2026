package com.example.minikviz

import android.R.attr.label
import android.os.Bundle
import android.text.InputType
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.TextLayoutInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.minikviz.ui.theme.MinikvizTheme

data class Vprasanje(
    val vpr: String,
    val odgovor: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KvizScreen()
        }
    }
}

@Composable
fun KvizScreen() {
    // ----------- Spremenljivke ---------
    val vprasanja = listOf(
        Vprasanje("Glavno mesto Slovenije?", "Ljubljana"),
        Vprasanje("Koliko je 5 + 7?", "12"),
        Vprasanje("Kateri jezik se uporablja za Android?", "Kotlin"),
        Vprasanje("Koliko je 10 * 10?", "100"),
        Vprasanje("Glavno mesto Francije?", "Pariz"),
        Vprasanje("Koliko mesecev ima leto?", "12"),
        Vprasanje("Katera je največja planeta v osončju?", "Jupiter"),
        Vprasanje("Koliko je 15 - 8?", "7"),
        Vprasanje("Glavno mesto Nemčije?", "Berlin"),
        Vprasanje("Koliko je 3 * 3?", "9")
    )
    var trenutnoVprasanje by remember { mutableStateOf(0) }
    var uporabnikovOdgovor by remember { mutableStateOf("") }
    var tocke by remember { mutableStateOf(0) }

    // ----------- UI ---------
    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(16.dp)
    ) {

        Text("MINI KVIZ - Tadej Murn", fontSize = 24.sp)

        if (trenutnoVprasanje < vprasanja.size) {
            Text("${vprasanja[trenutnoVprasanje].vpr}")

            OutlinedTextField(
                value = uporabnikovOdgovor,
                onValueChange = { uporabnikovOdgovor = it },
                label = { Text("Tukaj vnesite vaš odgovor") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    if (uporabnikovOdgovor.lowercase() == vprasanja[trenutnoVprasanje].odgovor.lowercase()) {
                        tocke++
                    }
                    trenutnoVprasanje++
                    uporabnikovOdgovor = ""
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Preveri odgovor")
            }
        } else {
            Text(
                text = "Kviz končan! Rezultat: $tocke / ${vprasanja.size}",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
