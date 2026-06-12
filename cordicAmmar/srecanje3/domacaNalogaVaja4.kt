package com.example.domaca_naloga_vaja_4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            Calculator()
        }
    }
}

@Composable
fun Calculator() {
    var st1 by remember { mutableStateOf("") }
    var st2 by remember { mutableStateOf("") }
    var rezultat by remember { mutableStateOf("") }

    Column {
        Spacer(
            modifier = Modifier.height(32.dp)
        )
        Text("Naslov", fontSize = 24.sp)

        Text("Število 1")
        TextField(
            value = st1,
            onValueChange = {
                st1 = it
            }
        )

        Text("Število 2")
        TextField(
            value = st2,
            onValueChange = {
                st2 = it
            }
        )

        Text("Rezultat seštevanja")
        Button(
            onClick = {
                val s1 = st1.toIntOrNull() ?: 0
                val s2 = st2.toIntOrNull() ?: 0
                rezultat = (s1 + s2).toString()
            }
        ) {
            Text("Seštej")
        }
        TextField(
            value = rezultat,
            onValueChange = {
                rezultat = it
            }
        )
    }
}