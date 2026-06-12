package com.example.tip_calculator_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            TipTimeLayout()
        }
    }
}

@Composable
fun TipTimeLayout()
{
    var amountInput:String by remember { mutableStateOf("") }

    val amount:Double = amountInput.toDoubleOrNull() ?: 0.0
    val tip:String = calculateTip(amount)

    Column(
        modifier = Modifier.fillMaxSize().statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Text(
            text = "Calculate Tip",
            modifier = Modifier.padding(all = 16.dp)
        )

        EditNumberField(
            value = amountInput,
            onValueC = { amountInput = it },
            modifier = Modifier.fillMaxWidth().padding(all = 16.dp)
        )

        Text(
            text = "Tip amount: $tip €",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(all = 16.dp)
        )
    }
}

@Composable
fun EditNumberField(value:String, onValueC:(String)->Unit, modifier: Modifier = Modifier)
{
    TextField(
        value = value,
        onValueChange = onValueC,
        modifier = modifier,
        label = { Text("Value") },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

private fun calculateTip(amount: Double, tipPercent: Double = 15.0): String
{
    val tip:Double = tipPercent / 100 * amount
    return String.format("%.2f", tip)
}