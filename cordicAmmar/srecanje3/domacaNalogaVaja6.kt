package com.example.domaca_naloga_vaja_6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Advanced_Calculator()
        }
    }
}

@Composable
fun Advanced_Calculator() {
    var number1 by remember { mutableStateOf("") }
    var number2 by remember { mutableStateOf("") }
    var selectedOperation by remember { mutableStateOf("+") }
    var result by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Spacer(
            modifier = Modifier.height(32.dp)
        )

        TextField(
            value = number1,
            onValueChange = {
                number1 = it
            },
            label = { Text("Prvo število") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        TextField(
            value = number2,
            onValueChange = {
                number2 = it
            },
            label = { Text("Drugo število") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        Row(modifier = Modifier.fillMaxWidth()) {
            listOf("+", "-", "*", "/", "%").forEach { operation ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.wrapContentWidth().padding(end = 8.dp)
                ) {
                    RadioButton(
                        selected = selectedOperation == operation,
                        onClick = { selectedOperation = operation }
                    )
                    Text(text = operation)
                }
            }
        }

        Button(
            onClick = {
                val num1 = number1.toDoubleOrNull()
                val num2 = number2.toDoubleOrNull()

                result = if (num1 == null || num2 == null) {
                    "Neveljavni vnosi"
                } else {
                    when (selectedOperation) {
                        "+" -> (num1 + num2).toString()
                        "-" -> (num1 - num2).toString()
                        "*" -> (num1 * num2).toString()
                        "/" -> if (num2 != 0.0) (num1 / num2).toString() else "Deljenje z ničlo ni dovoljeno"
                        "%" -> if (num2 != 0.0) (num1 % num2).toString() else "Deljenje z ničlo ni dovoljeno"
                        else -> "Neznana operacija"
                    }
                }
            },
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
        ) {
            Text("Izračunaj")
        }

        Text(
            text = "Rezultat: $result",
            modifier = Modifier.padding(top = 12.dp)
        )
    }
}