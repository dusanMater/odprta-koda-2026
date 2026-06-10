package com.example.seminarska

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.seminarska.ui.theme.SEMINARSKATheme

enum class Zaslon { ODKRIVANJE, PRILJUBLJENE }

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SEMINARSKATheme {
                var zaslon by remember { mutableStateOf(Zaslon.ODKRIVANJE) }
                when (zaslon) {
                    Zaslon.ODKRIVANJE -> ZaslonOdkrivanje(
                        naPriljubljene = { zaslon = Zaslon.PRILJUBLJENE }
                    )
                    Zaslon.PRILJUBLJENE -> ZaslonPriljubljene(
                        naNazaj = { zaslon = Zaslon.ODKRIVANJE }
                    )
                }
            }
        }
    }
}

@Composable
fun OAplikacijiDialog(onZapri: () -> Unit) {
    AlertDialog(
        onDismissRequest = onZapri,
        title = { Text("O Aplikaciji") },
        text = {
            Text(
                "POI Discovery App  Verzija: 1.0 MVP\n\n" +
                    "Namen: Odkrivanje točk zanimanja v bližini z uporabo OpenStreetMap API.\n\n" +
                    "Funkcije:\n" +
                    " • Avtomatsko odkrivanje POI-jev\n" +
                    " • Izbira tipa POI\n" +
                    " • Iskanje po kraju\n" +
                    " • Shranjevanje priljubljenih\n" +
                    " • Navigacija z gumbom"
            )
        },
        confirmButton = {
            TextButton(onClick = onZapri) { Text("OK") }
        }
    )
}
