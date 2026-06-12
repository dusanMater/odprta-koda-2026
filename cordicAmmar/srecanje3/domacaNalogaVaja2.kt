package com.example.domaca_naloga_vaja_2

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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat.enableEdgeToEdge
import com.example.domaca_naloga_vaja_2.ui.theme.Domaca_naloga_vaja_2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JedilniList()
        }
    }
}

@Composable
fun JedilniList() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
    ) {
        Text(
            "Jedilni list",
            color = Color.Red,
            fontSize = 35.sp,
            modifier = Modifier.padding(20.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),  // Add padding here instead
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Pizza Margarita", fontSize = 20.sp)
            Text("8,50€", color = Color.Green, fontSize = 20.sp)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Testenine", fontSize = 20.sp)
            Text("7,20€", color = Color.Green, fontSize = 20.sp)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Solata", fontSize = 20.sp)
            Text("5,90€", color = Color.Green, fontSize = 20.sp)
        }
    }
}