package com.example.igrakliker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IgraKliker()
        }
    }
}

// Paleta
private val barvaRdeca = Color(0xFFE63946)
private val barvaModra = Color(0xFF1D4ED8)

// Konstanta zmage
private const val MEJA_ZMAGE = 20

@Composable
fun IgraKliker() {
    var stevilo by remember { mutableStateOf(0) }

    // Izpeljani vrednosti — brez ločenega state-a
    val igraKoncana = stevilo >= MEJA_ZMAGE || stevilo <= -MEJA_ZMAGE
    val zmagovalec: String? = when {
        stevilo >= MEJA_ZMAGE -> "RDEČI"
        stevilo <= -MEJA_ZMAGE -> "MODRI"
        else -> null
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Naslov
        Text(
            text = "Igra: Hitri prsti",
            color = Color.Black,
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(11.dp)
        )

        // Zgornji (rdeči) gumb — +1
        IgralniGumb(
            barva = barvaRdeca,
            enabled = !igraKoncana,
            onClick = { stevilo += 1 },
            modifier = Modifier.weight(1f)
        )

        // Sredinski panel — število ali zmaga
        SredinskiPanel(
            stevilo = stevilo,
            zmagovalec = zmagovalec,
            onNovaIgra = { stevilo = 0 }
        )

        // Spodnji (modri) gumb — −1
        IgralniGumb(
            barva = barvaModra,
            enabled = !igraKoncana,
            onClick = { stevilo -= 1 },
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun IgralniGumb(
    barva: Color,
    enabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(barva)
            .clickable(enabled = enabled, onClick = onClick)
    )
}

@Composable
private fun SredinskiPanel(
    stevilo: Int,
    zmagovalec: String?,
    onNovaIgra: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        if (zmagovalec != null) {
            // Konec igre
            Text(
                text = "Zmagal je $zmagovalec!",
                color = Color.Black,
                fontSize = 47.sp,
                fontWeight = FontWeight.Bold
            )
            Button(onClick = onNovaIgra) {
                Text("Nova igra", fontSize = 32.sp)
            }
        } else {
            // Aktivna igra — read-only vnosno polje
            OutlinedTextField(
                value = stevilo.toString(),
                onValueChange = { },
                readOnly = true,
                textStyle = TextStyle(
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = Color.Black
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
