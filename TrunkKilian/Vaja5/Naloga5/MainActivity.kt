package com.example.am04

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.round

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App()
        }
    }
}

@Composable
fun App() {
    var valueM by remember { mutableStateOf("") }
    var tipPercent by remember { mutableStateOf("15") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .statusBarsPadding()
            .padding(28.dp)
    ) {
        Heading()
        EditNumberField(
            valueMon = valueM,
            onValueC = { valueM = it },
            labelText = "Value"
        )
        EditNumberField(
            valueMon = tipPercent,
            onValueC = { tipPercent = it },
            labelText = "Tip percent"
        )
        TipOutput(
            valueMoney = valueM,
            tipPercent = tipPercent
        )
    }
}

@Composable
fun Heading() {
    Text(
        text = "Calculate Tip",
        fontSize = 18.sp,
        modifier = Modifier.padding(top = 45.dp)
    )
}

@Composable
fun EditNumberField(
    valueMon: String,
    onValueC: (String) -> Unit,
    labelText: String
) {
    TextField(
        value = valueMon,
        onValueChange = onValueC,
        label = {
            Text(labelText)
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFFE8E1EB),
            unfocusedContainerColor = Color(0xFFE8E1EB),
            focusedIndicatorColor = Color(0xFF7053B5),
            unfocusedIndicatorColor = Color(0xFF7053B5)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 35.dp)
    )
}

@Composable
fun TipOutput(
    valueMoney: String,
    tipPercent: String
) {
    val tip = calculateTip(
        amount = valueMoney.toDoubleOrNull() ?: 0.0,
        percent = tipPercent.toDoubleOrNull() ?: 0.0
    )

    Text(
        text = "Tip amount: ${"%.2f".format(tip)} €",
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(top = 35.dp)
    )
}

fun calculateTip(
    amount: Double,
    percent: Double
): Double {
    val tip = amount * percent / 100
    return round(tip * 100) / 100
}
