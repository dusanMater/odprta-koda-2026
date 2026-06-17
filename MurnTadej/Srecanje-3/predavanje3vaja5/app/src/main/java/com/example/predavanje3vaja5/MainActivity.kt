package com.example.predavanje3vaja5   // ⚠️ preveri, da se ujema s tvojim projektom!

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NapredniKalkulator()
        }
    }
}



@Composable
fun NapredniKalkulator() {
    var st1 by remember { mutableStateOf("") }
    var st2 by remember { mutableStateOf("") }
    var rezultat by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(Modifier.height(32.dp))

        TextField(
            value = st1,
            onValueChange = { st1 = it },
            placeholder = { Text("Prvo stevilo") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        TextField(
            value = st2,
            onValueChange = { st2 = it },
            placeholder = { Text("Drugo stevilo") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Button(
                onClick = {
                    val a = st1.toIntOrNull() ?: 0
                    val b = st2.toIntOrNull() ?: 0
                    rezultat = (a + b).toString()
                },
                modifier = Modifier.weight(1f)
            ) { Text("+") }

            Button(
                onClick = {
                    val a = st1.toIntOrNull() ?: 0
                    val b = st2.toIntOrNull() ?: 0
                    rezultat = (a - b).toString()
                },
                modifier = Modifier.weight(1f)
            ) { Text("-") }

            Button(
                onClick = {
                    val a = st1.toIntOrNull() ?: 0
                    val b = st2.toIntOrNull() ?: 0
                    rezultat = (a * b).toString()
                },
                modifier = Modifier.weight(1f)
            ) { Text("*") }

            Button(
                onClick = {
                    val a = st1.toIntOrNull() ?: 0
                    val b = st2.toIntOrNull() ?: 0
                    rezultat = if (b != 0) (a / b).toString() else "Ne deli z 0"
                },
                modifier = Modifier.weight(1f)
            ) { Text("/") }

            Button(
                onClick = {
                    val a = st1.toIntOrNull() ?: 0
                    val b = st2.toIntOrNull() ?: 0
                    rezultat = if (b != 0) (a % b).toString() else "Ne deli z 0"
                },
                modifier = Modifier.weight(1f)
            ) { Text("%") }
        }

        Spacer(Modifier.height(12.dp))

        Text("Rezultat: $rezultat")
    }
}