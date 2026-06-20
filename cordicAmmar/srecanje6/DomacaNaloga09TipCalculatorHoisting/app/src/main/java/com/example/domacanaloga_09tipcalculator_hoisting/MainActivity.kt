package com.example.domacanaloga_09tipcalculator_hoisting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableInferredTarget
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.domacanaloga_09tipcalculator_hoisting.ui.theme.DomacaNaloga09TipCalculatorHoistingTheme

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
fun TipTimeLayout() {
    var amountInput by remember { mutableStateOf("") }
    var procentInput by remember { mutableStateOf("") }
    var roundUp by remember { mutableStateOf(false) }

    val amount = amountInput.toDoubleOrNull() ?: 0.0
    val procent = procentInput.toIntOrNull() ?: 0
    val tip = calculateTip(amount, procent, roundUp)


    Column (
        modifier = Modifier.fillMaxSize().statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            "Calculate Tip",
            modifier = Modifier.padding(16.dp)
        )

        EditNumberField(
            value = amountInput,
            onValueC = { amountInput = it },
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )

        EditNumberField(
            value = procentInput,
            onValueC = { procentInput = it },
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )

        RoundingUp(
            roundUp,
            {roundUp = it},
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
                .height(50.dp)
        )

        Text(
            "Tip amount: $tip €",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun EditNumberField(value: String, onValueC: (String) -> Unit, modifier: Modifier = Modifier) {
    TextField(
        value = value,
        onValueChange = onValueC,
        label = { Text("Vrednost") },
        modifier = modifier
    )
}

@Composable
fun RoundingUp(
    roundUp: Boolean,
    onRoundUpChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row (
        // Aligned in between, so that the text is on the left and the switch is on the right
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Text("Zaokroži navzgor?")
        Switch(
            checked = roundUp,
            onCheckedChange = onRoundUpChange
        )
    }
}

fun calculateTip(amount: Double, tipProcent: Int = 15, roundUp: Boolean = false): Double {
    var tip = amount * tipProcent / 100
    if (roundUp) {
        tip = kotlin.math.ceil(tip)
    }
    return tip
}