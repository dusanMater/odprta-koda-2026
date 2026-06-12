package com.example.domaca3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domaca3.ui.theme.Domaca3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Domaca3Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Kalkulator(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Kalkulator(modifier: Modifier = Modifier) {
    var stevilo1 by remember { mutableStateOf("") }
    var stevilo2 by remember { mutableStateOf("") }
    var rezultat by remember { mutableStateOf("") }


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Naslov",
            fontSize = 36.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(text = "Stevilo 1:", fontSize = 16.sp)
        TextField(
            value = stevilo1,
            onValueChange = { stevilo1 = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .background(Color(0xFFE6E1EB), shape = RoundedCornerShape(4.dp))
        )

        Text(text = "Stevilo 2:", fontSize = 16.sp)
        TextField(
            value = stevilo2,
            onValueChange = { stevilo2 = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .background(Color(0xFFE6E1EB), shape = RoundedCornerShape(4.dp))
        )

        Button(
            onClick = {
                val s1 = stevilo1.toDoubleOrNull()
                val s2 = stevilo2.toDoubleOrNull()
                if (s1 != null && s2 != null) {
                    val vsota = s1 + s2
                    rezultat = if (vsota % 1 == 0.0) vsota.toInt().toString() else vsota.toString()
                } else {
                    rezultat = "Napaka v vnosu"
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6750A4)),
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text(text = "Seštej", fontSize = 16.sp, color = Color.White)
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(Color(0xFFE6E1EB), shape = RoundedCornerShape(4.dp))
                .padding(horizontal = 16.dp),
            contentAlignment = androidx.compose.ui.Alignment.CenterStart
        ) {
            Text(text = "Rezultat: $rezultat", fontSize = 18.sp, color = Color.Black)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DNPreview() {
    Domaca3Theme {
        Kalkulator()
    }
}