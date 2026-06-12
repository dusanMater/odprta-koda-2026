package com.example.am04

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
import androidx.compose.foundation.layout.height
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
            MojUrnik()
        }
    }
}

@Composable
fun MojUrnik() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF24343B))
            .statusBarsPadding()
            .padding(start = 38.dp, top = 20.dp, end = 38.dp)
    ) {
        Text(
            text = "Moj urnik",
            color = Color.White,
            fontSize = 32.sp,
            modifier = Modifier.padding(bottom = 30.dp)
        )
        VrsticaUrnika("08:00", "Programiranje")
        VrsticaUrnika("09:00", "Baze podatkov")
        VrsticaUrnika("10:00", "Spletne strani")
        VrsticaUrnika("11:00", "Omrežja")
    }
}

@Composable
fun VrsticaUrnika(
    ura: String,
    predmet: String
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(bottom = 12.dp)
            .background(Color(0xFF394B54))
            .padding(start = 16.dp, end = 16.dp)
    ) {
        Text(
            text = ura,
            color = Color(0xFFFFCC73),
            fontSize = 20.sp
        )
        Text(
            text = predmet,
            color = Color.White,
            fontSize = 20.sp
        )
    }
}
