package com.example.tip_calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            Column(
                modifier = Modifier
                    .statusBarsPadding(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                var valueM: String by remember {
                    mutableStateOf("0")
                }

                var valueT: Double =
                    CalculateTip(valueM.toDouble())

                Heading(m = Modifier.padding(top = 40.dp, bottom = 20.dp)
                )

                EditNumberField(
                    valueMon = valueM.toDouble(),
                    onValueC = {
                        valueM = it
                    }
                )

                TipOutput(
                    valueMoney = valueT,
                    modifier = Modifier.padding(top = 20.dp)
                )
            }
        }
    }
}

@Composable
fun Heading(m: Modifier = Modifier)
{
    Text(
        text = "Calculate Tip",
        modifier = m
    )
}

@Composable
fun EditNumberField(
    valueMon: Double,
    onValueC: (String) -> Unit,
    modifier: Modifier = Modifier
)
{
    TextField(
        value = valueMon.toString(),
        readOnly = false,
        onValueChange = onValueC,
        label = {
            Text("Value")
        },
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
fun TipOutput(
    valueMoney: Double,
    modifier: Modifier = Modifier
)
{
    Text(
        text = "Tip amount: $valueMoney €",
        modifier = modifier
    )
}

fun CalculateTip(amount: Double): Double
{
    val tip: Double = amount * (15.0 / 100)

    return tip
}