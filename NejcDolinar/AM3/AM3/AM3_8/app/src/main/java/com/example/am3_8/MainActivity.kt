package com.example.am3_8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Display()
        }
    }
}

@Composable
fun Display() {
    var stevilo by remember { mutableStateOf(0) }
    var zmagovalec by remember { mutableStateOf("") }
    val konec = zmagovalec.isNotEmpty()

    Column(modifier = Modifier.fillMaxSize()) {

        // Rdeči gumb (zgoraj)
        Button(
            onClick = {
                if (!konec) {
                    stevilo += 1
                    if (stevilo >= 20) zmagovalec = "Rdeči zmagal!"
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f),           // ← polovica ekrana
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
        ) {}

        // Vnosno polje na sredini
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = if (konec) zmagovalec else stevilo.toString(),
                onValueChange = {},
                readOnly = true,
                label = { Text("Igrica kdo bo hitrejši") }
            )
        }

        // Modri gumb (spodaj)
        Button(
            onClick = {
                if (!konec) {
                    stevilo -= 1
                    if (stevilo <= -20) zmagovalec = "Modri zmagal!"
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(1f),             // ← preostala polovica
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
        ) {}
    }
}