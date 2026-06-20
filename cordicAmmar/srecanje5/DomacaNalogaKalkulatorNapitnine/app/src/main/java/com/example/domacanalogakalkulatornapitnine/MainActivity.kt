package com.example.domacanalogakalkulatornapitnine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val priceInput = remember { mutableStateOf("") }
            val tipProcent = remember { mutableStateOf("") }
            var roundUp by remember { mutableStateOf(false) }
            val tipAmount = calculateTip(
                priceInput.value.toDoubleOrNull() ?: 0.0,
                tipProcent.value.toDoubleOrNull() ?: 0.0,
                roundUp)

            Column {
                Title(
                    Modifier
                        .statusBarsPadding()
                        .fillMaxWidth()
                )

                InputField(
                    priceInput.value,
                    {priceInput.value = it},
                    "Račun",
                    Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )

                InputField(
                    tipProcent.value,
                    {tipProcent.value = it},
                    "Napitnina - Procent",
                    Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )

                RoundingUp(
                    roundUp,
                    {roundUp = it},
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp)
                        .height(50.dp)
                )

                Result(
                    tipAmount,
                    Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                )

            }
        }
    }
}

@Composable
fun Title(modifier: Modifier = Modifier) {
    Text(
        "Kalkulator napitnine",
        modifier = modifier,
        textAlign = TextAlign.Center,
        fontSize = 30.sp
    )
}

@Composable
fun InputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
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

@Composable
fun Result(amount: Double, modifier: Modifier = Modifier) {
    Text(
        "Napitnina: $amount €",
        modifier = modifier,
        textAlign = TextAlign.Center,
        fontSize = 20.sp
    )
}

fun calculateTip(amount: Double, tipProcent: Double, roundUp: Boolean): Double {
    var tip = amount * tipProcent / 100
    if (roundUp) {
        tip = kotlin.math.ceil(tip)
    }
    return tip
}