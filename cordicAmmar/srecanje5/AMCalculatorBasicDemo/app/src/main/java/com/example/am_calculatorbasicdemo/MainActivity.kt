package com.example.am_calculatorbasicdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val valueM = remember { mutableStateOf("") }
            val valueT = calculateTip(valueM.value.toDoubleOrNull() ?: 0.0)

            Column (
                modifier = Modifier.statusBarsPadding()
            ) {
                Heading()
                EditNumberField(valueM.value, { valueM.value = it })
                TipOutput(valueT)
            }
        }
    }
}

@Composable
fun Heading() {
    Text(
        text = "Calculate Tip"
    )
}

@Composable
fun EditNumberField(valueM: String, onValueC: (String) -> Unit) {
    TextField(
        value = valueM,
        onValueChange = onValueC,
        label = { Text("Value") }
    )
}

@Composable
fun TipOutput(valueMoney: Double) {
    Text(
        text = "Tip Amount: $valueMoney €"
    )
}

fun calculateTip(amount: Double): Double {
    return amount * 15 / 100
}