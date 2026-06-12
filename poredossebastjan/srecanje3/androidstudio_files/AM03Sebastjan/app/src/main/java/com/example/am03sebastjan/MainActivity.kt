package com.example.am03sebastjan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
    var st1: String by remember { mutableStateOf("") }
    var st2: String by remember { mutableStateOf("") }
    var rez: String by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {

        Spacer(
            modifier = Modifier.height(32.dp)
        )

        Text(
            text = "Kalkulator",
            fontSize = 24.sp
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        Text("Stevilo 1:")

        TextField(
            value = st1,
            onValueChange = {
                st1 = it
            }
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        Text(
            text = "Stevilo 2:"
        )

        TextField(
            value = st2,
            onValueChange = {
                st2 = it
            }
        )

        Spacer(
            modifier = Modifier.height(15.dp)
        )

        Button(
            onClick = {

                var s1:Int = st1.toInt()
                var s2:Int = st2.toInt()

                var r:Int = s1 + s2

                rez = r.toString()
            }
        )
        {
            Text("Seštej")
        }

        Spacer(
            modifier = Modifier.height(15.dp)
        )

        TextField(
            value = rez,
            onValueChange = {
                rez = it
            },
            modifier = Modifier.width(280.dp)
        )
    }
}
