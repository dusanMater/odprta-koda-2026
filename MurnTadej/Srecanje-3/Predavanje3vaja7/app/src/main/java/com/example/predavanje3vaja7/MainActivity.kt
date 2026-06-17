package com.example.predavanje3vaja7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.predavanje3vaja7.ui.theme.Predavanje3vaja7Theme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Kliker()
        }
    }
}



@Composable
fun Kliker() {
    var klik by remember { mutableStateOf(0) }
    val konec = klik >= 20 || klik <= -20
    val zmagovalec = if (klik >= 20) "Rdeči" else "Modri"


    Column(modifier = Modifier.fillMaxSize()) {

        Button(
            onClick = { klik++ },
            enabled = !konec,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFE91E63),
                contentColor = Color.White),
            shape = RectangleShape,
            modifier = Modifier
                .fillMaxWidth()
                .weight(4f)
        ) { Text(
            text = "+1",
            fontSize = 30.sp
        ) }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (konec) "Zmagal je $zmagovalec!" else "Trenutni rezultat je: $klik",
                fontSize = 30.sp
            )
        }


        Button(
            onClick = { klik-- },
            enabled = !konec,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2196F3),
                contentColor = Color.White),
            shape = RectangleShape,
            modifier = Modifier
                .fillMaxWidth()
                .weight(4f)
        ) { Text(
            text = "-1",
            fontSize = 30.sp
        ) }
    }
}
