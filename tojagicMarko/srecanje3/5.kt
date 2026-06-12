package com.example.domaca3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domaca3.ui.theme.Domaca3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Domaca3Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    KalkulatorGumbi(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun KalkulatorGumbi(modifier: Modifier = Modifier) {
    var stevilo1 by remember { mutableStateOf("") }
    var stevilo2 by remember { mutableStateOf("") }
    var rezultat by remember { mutableStateOf("") }

    val operacije = listOf("+", "-", "*", "/", "%")

    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {

        OutlinedTextField(
            value = stevilo1,
            onValueChange = { stevilo1 = it },
            label = { Text("Prvo število") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = stevilo2,
            onValueChange = { stevilo2 = it },
            label = { Text("Drugo število") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            operacije.forEach { op ->
                Button(
                    onClick = {
                        val s1 = stevilo1.toDoubleOrNull()
                        val s2 = stevilo2.toDoubleOrNull()
                        if (s1 != null && s2 != null) {
                            val izracun = when (op) {
                                "+" -> s1 + s2
                                "-" -> s1 - s2
                                "*" -> s1 * s2
                                "/" -> if (s2 != 0.0) s1 / s2 else Double.NaN
                                "%" -> s1 % s2
                                else -> 0.0
                            }
                            rezultat = "Rezultat: $izracun"
                        } else {
                            rezultat = "Vnesite številki"
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6750A4)),
                    shape = RoundedCornerShape(50),
                    modifier = Modifier.weight(1f).padding(2.dp)
                ) {
                    Text(text = op, fontSize = 18.sp, color = Color.White)
                }
            }
        }

        if (rezultat.isNotEmpty()) {
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = rezultat, fontSize = 18.sp)
        }
    }
}