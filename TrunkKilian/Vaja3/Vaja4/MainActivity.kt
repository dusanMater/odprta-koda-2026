package com.example.am04

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Sestevalnik()
        }
    }
}

@Composable
fun Sestevalnik() {
    var stevilo1 by remember { mutableStateOf("") }
    var stevilo2 by remember { mutableStateOf("") }
    var rezultat by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .statusBarsPadding()
    ) {
        Text(
            text = "Naslov",
            color = Color.Black,
            fontSize = 30.sp
        )
        Text("Stevilo 1:")
        PoljeZaVnos(
            vrednost = stevilo1,
            onValueChange = { stevilo1 = it }
        )
        Text("Stevilo 2:")
        PoljeZaVnos(
            vrednost = stevilo2,
            onValueChange = { stevilo2 = it }
        )
        Button(
            onClick = {
                val prvo = stevilo1.toDoubleOrNull() ?: 0.0
                val drugo = stevilo2.toDoubleOrNull() ?: 0.0
                rezultat = (prvo + drugo).toString()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF6B4BB3),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(18.dp),
            modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
        ) {
            Text("Seštej")
        }
        PoljeZaVnos(
            vrednost = rezultat,
            onValueChange = { rezultat = it }
        )
    }
}

@Composable
fun PoljeZaVnos(
    vrednost: String,
    onValueChange: (String) -> Unit
) {
    BasicTextField(
        value = vrednost,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier
            .fillMaxWidth(0.74f)
            .height(54.dp)
            .background(Color(0xFFE6DFEA), RoundedCornerShape(4.dp))
            .border(width = 1.dp, color = Color.Gray)
            .padding(8.dp)
    )
}
