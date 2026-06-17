package com.example.tipcalculatornapredni

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tipcalculatornapredni.ui.theme.TipCalculatorNapredniTheme

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

    var amountInput: String by remember { mutableStateOf("") }
    var tipInput: String by remember { mutableStateOf("") }

    val amount: Double = amountInput.toDoubleOrNull() ?: 0.0
    val tipPercent: Double = tipInput.toDoubleOrNull() ?: 0.0
    val tip: String = calculateTip(amount, tipPercent)

    Column(
        modifier = Modifier.fillMaxSize().statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Text(
            text = "Calculate Tip",
            fontSize = 24.sp,
            modifier = Modifier.padding(16.dp)
        )

        EditNumberField(
            value = amountInput,
            onValueC = { amountInput = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        EditTipField(
            value = tipInput,
            onValueC = { tipInput = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        Text(
            text = "Tip amount: $tip €",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun EditNumberField(
    value: String,
    onValueC: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueC,
        modifier = modifier,
        label = { Text("Cost of Service") },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

@Composable
fun EditTipField(
    value: String,
    onValueC: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueC,
        modifier = modifier,
        label = { Text("Tip (%)") },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

private fun calculateTip(
    amount: Double,
    tipPercent: Double
): String {
    val tip = tipPercent / 100 * amount
    return String.format("%.2f", tip)
}