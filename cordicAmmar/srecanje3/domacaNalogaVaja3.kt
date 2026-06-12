package com.example.domaca_naloga_vaja_3

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
            Urnik()
        }
    }
}

@Composable
fun Urnik() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
            .padding(20.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {

        Text(
            "Moj urnik",
            color = Color.White,
            fontSize = 28.sp
        )

        Predmet("08:00", "Matematika")
        Predmet("10:00", "Programiranje")
        Predmet("12:00", "Angleščina")
        Predmet("14:00", "Kotlin")
    }
}

@Composable
fun Predmet(ura:String,predmet:String){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFF555555))
            .padding(horizontal = 12.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {

        Text(ura,color=Color.Yellow)
        Text(predmet,color=Color.White)
    }
}