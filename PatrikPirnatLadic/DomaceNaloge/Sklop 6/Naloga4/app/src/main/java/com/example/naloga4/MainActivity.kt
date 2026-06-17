package com.example.naloga4

import android.os.Bundle
import android.view.Display
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.naloga4.Display
import com.example.naloga4.ui.theme.Naloga4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Naloga4Theme {
                Display()
            }
        }
    }
}

@Composable
fun Display() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier.weight(1f)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .background(color = Color(0xFFEADDFF))
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text("Text composable", fontWeight = Bold)
                Text("Displays text, and follows the recomanded Material Design guidlines.")
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .background(color = Color(0xFFD0BCFF))
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text("Image composable", fontWeight = Bold)
                Text("Creates a composable that lays out and draws a given Painter class object.")
            }
        }
        Row(
            modifier = Modifier.weight(1f)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .background(color = Color(0xFFB69DF8))
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text("Row composable", fontWeight = Bold)
                Text("A layout composable that places its children in a horizontal sequence.")
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .background(color = Color(0xFFF6EDFF))
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text("Column composable", fontWeight = Bold)
                Text("A layout composable that places its children in a vertical sequence.")
            }
        }
    }
}