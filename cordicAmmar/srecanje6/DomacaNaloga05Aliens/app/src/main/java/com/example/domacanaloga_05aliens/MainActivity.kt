package com.example.domacanaloga_05aliens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
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
            Column {
                Row (
                    modifier = Modifier.statusBarsPadding().height(400.dp)
                ) {
                    Alien(Color.Red, Modifier.padding(end = 25.dp))
                    Alien(Color.Green, Modifier.padding(end = 25.dp))
                    Alien(Color.Red, Modifier.padding(end = 25.dp))
                }
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Alien(Color.Green)
                }
            }
        }
    }
}

@Composable
fun Alien(color: Color, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.alien),
        contentDescription = "Alien",
        modifier = modifier,
        colorFilter = ColorFilter.tint(color)
    )
}