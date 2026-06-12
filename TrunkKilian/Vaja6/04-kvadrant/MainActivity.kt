package com.example.am04

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
import androidx.compose.ui.text.style.TextAlign
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
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier.weight(1f)
        ) {
            Kvadrant("Text composable", "Displays text and follows the recommended Material Design guidelines.", Color(0xFFEADDFF), Modifier.weight(1f))
            Kvadrant("Image composable", "Creates a composable that lays out and draws a given Painter class object.", Color(0xFFD0BCFF), Modifier.weight(1f))
        }
        Row(
            modifier = Modifier.weight(1f)
        ) {
            Kvadrant("Row composable", "A layout composable that places its children in a horizontal sequence.", Color(0xFFB69DF8), Modifier.weight(1f))
            Kvadrant("Column composable", "A layout composable that places its children in a vertical sequence.", Color(0xFFF6EDFF), Modifier.weight(1f))
        }
    }
}

@Composable
fun Kvadrant(
    naslov: String,
    opis: String,
    barva: Color,
    modifier: Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .background(barva)
            .padding(16.dp)
    ) {
        Text(
            text = naslov,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = opis,
            textAlign = TextAlign.Justify
        )
    }
}
