package com.example.am3_5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.Button
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
    var selectedOp by remember { mutableStateOf("+") }  // ← trenutno izbrana operacija

    val operacije = listOf("+", "-", "*", "/", "%")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = a,
            onValueChange = { a = it },
            label = { Text("Prvo število") }
        )
        OutlinedTextField(
            value = b,
            onValueChange = { b = it },
            label = { Text("Drugo število") }
        )

        // RadioButtoni za izbiro operacije
        Row {
            operacije.forEach { op ->
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedOp == op,      // ← označen če je ta op izbrana
                        onClick = { selectedOp = op }     // ← ob kliku nastavi operacijo
                    )
                    Text(op)
                }
            }
        }

        // En gumb za izračun
        Button(onClick = {
            val c = a.toDoubleOrNull() ?: 0.0
            val d = b.toDoubleOrNull() ?: 0.0
            rezultat = when (selectedOp) {
                "+" -> (c + d).toString()
                "-" -> (c - d).toString()
                "*" -> (c * d).toString()
                "/" -> if (d != 0.0) (c / d).toString() else "Deljenje z 0!"
                "%" -> (c % d).toString()
                else -> ""
            }
        }) {
            Text("Izračunaj")
        }

        Text("Rezultat: $rezultat")
    }
}