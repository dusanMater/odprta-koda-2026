package com.example.domaca_naloga_vaja_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.ui.Alignment


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Vizitka()
        }
    }
}

@Composable
fun Vizitka() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE8F5E9))
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Ammar Ćordić",
            fontSize = 30.sp
        )

        Text(
            text = "Programer",
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Univerza Alma Mater Europaea",
            color = Color.Blue
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "ammar.cordic@almamater.si"
        )
    }
}