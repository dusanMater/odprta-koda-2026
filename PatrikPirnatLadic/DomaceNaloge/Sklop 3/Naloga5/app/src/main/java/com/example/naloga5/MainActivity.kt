package com.example.naloga5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
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
    var a by remember { mutableStateOf("") }
    var b by remember { mutableStateOf("") }
    var rezultat by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(start = 16.dp, top = 48.dp, end = 16.dp, bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = a,
            onValueChange = { a = it },
            label = { Text("Število 1") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = b,
            onValueChange = { b = it },
            label = { Text("Število 2") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )

        Row(
            modifier = Modifier.padding(top = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = {
                val c = a.toDoubleOrNull() ?: 0.0
                val d = b.toDoubleOrNull() ?: 0.0
                rezultat = (c + d).toString()
            }) {
                Text("+")
            }
            Button(onClick = {
                val c = a.toDoubleOrNull() ?: 0.0
                val d = b.toDoubleOrNull() ?: 0.0
                rezultat = (c - d).toString()
            }) {
                Text("-")
            }
            Button(onClick = {
                val c = a.toDoubleOrNull() ?: 0.0
                val d = b.toDoubleOrNull() ?: 0.0
                rezultat = (c * d).toString()
            }) {
                Text("*")
            }
            Button(onClick = {
                val c = a.toDoubleOrNull() ?: 0.0
                val d = b.toDoubleOrNull() ?: 0.0
                rezultat = if (d != 0.0) (c / d).toString() else "Delenje z 0!"
            }) {
                Text("/")
            }
            Button(onClick = {
                val c = a.toDoubleOrNull() ?: 0.0
                val d = b.toDoubleOrNull() ?: 0.0
                rezultat = if (d != 0.0) (c % d).toString() else "Delenje z 0!"
            }) {
                Text("%")
            }
        }

        Text(
            text = "Rezultat: $rezultat",
            modifier = Modifier.padding(top = 12.dp)
        )
    }
}
