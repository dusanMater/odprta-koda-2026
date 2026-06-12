package com.example.domaca3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
                    KalkulatorRadio(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun KalkulatorRadio(modifier: Modifier = Modifier) {
    var stevilo1 by remember { mutableStateOf("") }
    var stevilo2 by remember { mutableStateOf("") }
    var izbranaOperacija by remember { mutableStateOf("+") }
    var rezultat by remember { mutableStateOf("Rezultat: ") }

    val operacije = listOf("+", "-", "*", "/", "%")

    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {

        OutlinedTextField(
            value = stevilo1,
            onValueChange = { stevilo1 = it },
            label = { Text("Prvo število") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)
        )

        OutlinedTextField(
            value = stevilo2,
            onValueChange = { stevilo2 = it },
            label = { Text("Drugo število") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )

        Text(text = "Izberi operacijo:", fontSize = 16.sp, modifier = Modifier.padding(bottom = 8.dp))

        // Vrstica s kvadratki za izbiro operacije
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            operacije.forEach { op ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp)
                        .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
                        .selectable(
                            selected = (izbranaOperacija == op),
                            onClick = { izbranaOperacija = op }
                        )
                        .padding(4.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = (izbranaOperacija == op),
                            onClick = { izbranaOperacija = op }
                        )
                        Text(text = op, fontSize = 16.sp, modifier = Modifier.padding(start = 2.dp))
                    }
                }
            }
        }

        // Glavni gumb "Izračunaj"
        Button(
            onClick = {
                val s1 = stevilo1.toDoubleOrNull()
                val s2 = stevilo2.toDoubleOrNull()
                if (s1 != null && s2 != null) {
                    val izracun = when (izbranaOperacija) {
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
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier.fillMaxWidth().height(48.dp)
        ) {
            Text(text = "Izračunaj", fontSize = 16.sp, color = Color.White)
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text(text = rezultat, fontSize = 18.sp)
    }
}