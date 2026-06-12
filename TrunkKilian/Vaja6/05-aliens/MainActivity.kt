package com.example.am04

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AlienScreen()
        }
    }
}

@Composable
fun AlienScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row {
            Robotek(Color.Red, Modifier.padding(12.dp))
            Robotek(Color.Blue, Modifier.padding(12.dp))
        }
        Row {
            Robotek(Color.Green, Modifier.padding(12.dp))
            Robotek(Color.Magenta, Modifier.padding(12.dp))
        }
    }
}

@Composable
fun Robotek(
    barva: Color,
    modifier: Modifier
) {
    Image(
        painter = painterResource(R.drawable.alien),
        contentDescription = null,
        colorFilter = ColorFilter.tint(barva),
        modifier = modifier.size(120.dp)
    )
}
