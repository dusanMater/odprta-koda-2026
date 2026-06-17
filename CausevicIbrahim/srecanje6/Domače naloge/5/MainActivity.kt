package com.example.domacanaloga_6_5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
        setContent {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Robot(
                    barva = Color.Blue,
                    modifier = Modifier.size(150.dp)
                )
            }
        }
    }
}

@Composable
fun Robot(
    barva: Color,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = R.drawable.alien),
        contentDescription = "Robotek Alien",
        colorFilter = ColorFilter.tint(barva),
        modifier = modifier
    )
}