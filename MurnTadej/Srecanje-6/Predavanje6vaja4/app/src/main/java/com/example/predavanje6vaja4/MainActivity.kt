package com.example.predavanje6vaja4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Kvadranti()
        }
    }
}

@Composable
fun Kvadranti() {
    Column(modifier = Modifier.fillMaxSize()) {


        // ZGORNJA vrstica – polovica višine
        Row(modifier = Modifier.weight(1f)) {
            Kvadrant(
                naslov = "Text composable",
                besedilo = "Displays text and follows the recommended Material Design guidelines.",
                barva = Color(0xFFEADDFF),
                modifier = Modifier.weight(1f)
            )
            Kvadrant(
                naslov = "Image composable",
                besedilo = "Creates a composable that lays out and draws a given Painter class object.",
                barva = Color(0xFFD0BCFF),
                modifier = Modifier.weight(1f)
            )
        }


        // SPODNJA vrstica – polovica višine
        Row(modifier = Modifier.weight(1f)) {
            Kvadrant(
                naslov = "Row composable",
                besedilo = "A layout composable that places its children in a horizontal sequence.",
                barva = Color(0xFFB69DF8),
                modifier = Modifier.weight(1f)
            )
            Kvadrant(
                naslov = "Column composable",
                besedilo = "A layout composable that places its children in a vertical sequence.",
                barva = Color(0xFFF6EDFF),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun Kvadrant(naslov: String, besedilo: String, barva: Color, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(barva)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = naslov,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(text = besedilo)
    }
}