package com.example.am3_4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Display()
        }
    }
}

@Composable
fun Display() {
    // State za oba inputa in rezultat
    var stevilka1 by remember { mutableStateOf("") }
    var stevilka2 by remember { mutableStateOf("") }
    var rezultat by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row {
            Text("Naslov")
        }

        Column {
            OutlinedTextField(
                value = stevilka1,
                onValueChange = { stevilka1 = it },
                label = { Text("Število 1") }
            )
            OutlinedTextField(
                value = stevilka2,
                onValueChange = { stevilka2 = it },
                label = { Text("Število 2") }
            )

            Button(onClick = {
                val a = stevilka1.toDoubleOrNull() ?: 0.0
                val b = stevilka2.toDoubleOrNull() ?: 0.0
                rezultat = (a + b).toString()
            }) {
                Text("Seštej")
            }

            Text("Rezultat: $rezultat")
        }
    }
}