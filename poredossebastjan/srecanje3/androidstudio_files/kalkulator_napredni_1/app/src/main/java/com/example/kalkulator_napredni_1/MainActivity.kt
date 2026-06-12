package com.example.kalkulator_napredni_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Spacer(modifier = Modifier.height(40.dp))

        TextField(
            value = st1,
            onValueChange = {
                st1 = it
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Prvo število")
            }
        )

        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = st2,
            onValueChange = {
                st2 = it
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Drugo število")
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Button(
                onClick = {

                    var s1 = st1.toDouble()
                    var s2 = st2.toDouble()

                    rez = (s1 + s2).toString()
                },

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6A4FB3)
                )
            ) {
                Text("+")
            }

            Button(
                onClick = {

                    var s1 = st1.toDouble()
                    var s2 = st2.toDouble()

                    rez = (s1 - s2).toString()
                },

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6A4FB3)
                )
            ) {
                Text("-")
            }

            Button(
                onClick = {

                    var s1 = st1.toDouble()
                    var s2 = st2.toDouble()

                    rez = (s1 * s2).toString()
                },

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6A4FB3)
                )
            ) {
                Text("*")
            }

            Button(
                onClick = {

                    var s1 = st1.toDouble()
                    var s2 = st2.toDouble()

                    rez = (s1 / s2).toString()
                },

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6A4FB3)
                )
            ) {
                Text("/")
            }

            Button(
                onClick = {

                    var s1 = st1.toDouble()
                    var s2 = st2.toDouble()

                    rez = (s1 % s2).toString()
                },

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6A4FB3)
                )
            ) {
                Text("%")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Rezultat: $rez"
        )
    }
}