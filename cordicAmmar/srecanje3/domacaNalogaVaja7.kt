package com.example.domaca_naloga_vaja_7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Clicker()
        }
    }
}

@Composable
fun Clicker() {

    var count by remember {
        mutableStateOf(0)
    }

    Button(
        onClick = {
            count++
        },
        modifier = Modifier.fillMaxSize(),
        shape = RoundedCornerShape(0.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Cyan,
            contentColor = Color.White
        )
    ) {

        Text(
            text = count.toString(),
            fontSize = 50.sp
        )
    }
}