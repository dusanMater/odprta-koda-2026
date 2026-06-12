package com.example.kliker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Kliker()
        }
    }
}

@Composable
fun Kliker()
{
    var stevilo by remember { mutableStateOf(0) }

    Button(
        onClick = {
            stevilo++
        },

        modifier = Modifier.fillMaxSize(),
        shape = RectangleShape
    ) {

        Text(
            text = stevilo.toString(),
            fontSize = 30.sp
        )
    }
}