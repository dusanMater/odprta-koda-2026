package com.example.naloga3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(Color(0xFF1A1A2E))
            .padding(top = 24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Moj urnik",
            color = Color.White,
            fontSize = 25.sp,
            modifier = Modifier.padding(16.dp)
        )

        val urnik = listOf(
            "08:00" to "Programiranje",
            "09:00" to "Baze podatkov",
            "10:00" to "Spletne strani",
            "11:00" to "Omrežja"
        )

        urnik.forEach { (ura, predmet) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp)
                    .background(Color(0xFF2A2A3E)),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(ura, color = Color(0xFFFFAA00), modifier = Modifier.padding(12.dp))
                Text(predmet, color = Color.White, modifier = Modifier.padding(12.dp))
            }
        }
    }
}
