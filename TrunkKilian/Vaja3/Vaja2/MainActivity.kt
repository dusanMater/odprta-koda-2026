package com.example.am04

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
            .background(Color(0xFFFFF4DF))
            .statusBarsPadding()
            .padding(start = 24.dp, top = 20.dp, end = 24.dp)
    ) {
        Text(
            text = "Jedilni list",
            color = Color(0xFFE34A05),
            fontSize = 30.sp,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        VrsticaJedilnika("Pizza margarita", "8,50 €")
        VrsticaJedilnika("Testenine", "7,20 €")
        VrsticaJedilnika("Solata", "5,90 €")
    }
}

@Composable
fun VrsticaJedilnika(
    jed: String,
    cena: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 14.dp)
    ) {
        Text(
            text = jed,
            color = Color.Black,
            fontSize = 20.sp,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = cena,
            color = Color(0xFF178133),
            fontSize = 20.sp
        )
    }
}
