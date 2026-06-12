package com.example.kalkulator_napredni_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Izgled()
        }
    }
}

@Composable
fun Izgled()
{
    var st1:String by remember { mutableStateOf("") }
    var st2:String by remember { mutableStateOf("") }
    var rez:String by remember { mutableStateOf("") }

    var operacija:String by remember { mutableStateOf("+") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Spacer(modifier = Modifier.height(30.dp))

        TextField(
            value = st1,
            onValueChange = {
                st1 = it
            },

            label = {
                Text("Prvo število")
            },

            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = st2,
            onValueChange = {
                st2 = it
            },

            label = {
                Text("Drugo število")
            },

            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text("Izberi operacijo:")

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            listOf("+", "-", "*", "/", "%").forEach { op ->

                OutlinedButton(
                    onClick = {
                        operacija = op
                    },

                    shape = RectangleShape,

                    contentPadding = PaddingValues(
                        horizontal = 2.dp,
                        vertical = 0.dp
                    )
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        RadioButton(
                            selected = operacija == op,
                            onClick = {
                                operacija = op
                            }
                        )

                        Text(op)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {

                var s1 = st1.toDouble()
                var s2 = st2.toDouble()

                var r = 0.0

                when(operacija)
                {
                    "+" -> r = s1 + s2
                    "-" -> r = s1 - s2
                    "*" -> r = s1 * s2
                    "/" -> r = s1 / s2
                    "%" -> r = s1 % s2
                }

                rez =
                    if(r % 1 == 0.0)
                        r.toInt().toString()
                    else
                        r.toString()
            },

            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Izračunaj")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Rezultat: $rez"
        )
    }
}
