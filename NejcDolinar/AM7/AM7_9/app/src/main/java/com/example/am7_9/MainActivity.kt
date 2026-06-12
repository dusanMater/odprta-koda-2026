package com.example.am7_9

import android.os.Bundle
import android.view.Display
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.am7_9.ui.theme.AM7_9Theme
import kotlin.math.ceil

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AM7_9Theme {
                Display()
            }
        }
    }
}

@Composable
fun InputField(modified: Modifier, value: String, onValueChange: (String) -> Unit, label: String) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = modified
    )
}

// Funkcija za prikaz stikala Round Up
@Composable
fun RoundUpToggle(modified: Modifier, roundUp: Boolean, onRoundUpChange: (Boolean) -> Unit) {
    androidx.compose.foundation.layout.Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modified
    ) {
        Text(
            text = "Round up tip?",
            modifier = Modifier.weight(1f)
        )
        Switch(
            checked = roundUp,
            onCheckedChange = onRoundUpChange
        )
    }
}

// Funkcija za izračun napitnine
fun calculateTip(modified: Double, tipPercent: Double, roundUp: Boolean): Double {
    var tip = modified * (tipPercent / 100)
    if (roundUp) tip = ceil(tip)
    return tip
}

// Funkcija za prikaz rezultata
@Composable
fun TipResult(modified: Modifier, tipAmount: Double) {
    Text(
        text = "Tip Amount: $%.2f".format(tipAmount),
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        modifier = modified
    )
}

@Composable
fun Display() {
    var costInput by remember { mutableStateOf("") }
    var tipInput by remember { mutableStateOf("") }
    var roundUp by remember { mutableStateOf(false) }

    val cost = costInput.toDoubleOrNull() ?: 0.0
    val tipPercent = tipInput.toDoubleOrNull() ?: 0.0
    val tipAmount = calculateTip(cost, tipPercent, roundUp)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Calculate Tip",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))

       InputField(
            modified = Modifier,
            value = costInput,
            onValueChange = { costInput = it },
            label = "Cost of Service"
        )
        Spacer(modifier = Modifier.height(8.dp))

        InputField(
            modified = Modifier,
            value = tipInput,
            onValueChange = { tipInput = it },
            label = "Tip (%)"
        )
        Spacer(modifier = Modifier.height(8.dp))

        RoundUpToggle(
            modified = Modifier,
            roundUp = roundUp,
            onRoundUpChange = { roundUp = it }
        )
        Spacer(modifier = Modifier.height(16.dp))

        TipResult(
            modified = Modifier,
            tipAmount = tipAmount
        )
    }
}