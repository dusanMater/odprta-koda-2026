package com.example.domacanaloga_6_4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(modifier = Modifier.fillMaxSize()) {
                Row(modifier = Modifier.weight(1f)) {
                    KvadrantKartica(
                        naslov = "Text composable",
                        opis = "Displays text and follows the recommended Material Design guidelines.",
                        barvaOzadja = Color(0xFFEADDFF),
                        modifier = Modifier.weight(1f)
                    )
                    KvadrantKartica(
                        naslov = "Image composable",
                        opis = "Creates a composable that lays out and draws a given Painter class object.",
                        barvaOzadja = Color(0xFFD0BCFF),
                        modifier = Modifier.weight(1f)
                    )
                }
                Row(modifier = Modifier.weight(1f)) {
                    KvadrantKartica(
                        naslov = "Row composable",
                        opis = "A layout composable that places its children in a horizontal sequence.",
                        barvaOzadja = Color(0xFFB69DF8),
                        modifier = Modifier.weight(1f)
                    )
                    KvadrantKartica(
                        naslov = "Column composable",
                        opis = "A layout composable that places its children in a vertical sequence.",
                        barvaOzadja = Color(0xFFF6EDFF),
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
fun KvadrantKartica(
    naslov: String,
    opis: String,
    barvaOzadja: Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(barvaOzadja)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = naslov,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = opis,
            color = Color.Black,
            textAlign = TextAlign.Justify
        )
    }
}