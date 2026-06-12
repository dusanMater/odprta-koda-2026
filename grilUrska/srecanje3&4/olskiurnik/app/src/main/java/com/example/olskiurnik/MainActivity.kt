package com.example.olskiurnik

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.olskiurnik.ui.theme.ŠolskiUrnikTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SolskiUrnik()
        }
    }
}

@Composable
fun SolskiUrnik(modifier: Modifier = Modifier) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color(0xFF272729))
        .padding(top=20.dp, bottom=500.dp, start=15.dp, end=15.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        ) {
        Row(modifier = Modifier.padding(all=10.dp)){
            Text(
                text = "Moj urnik",
                color = Color.White,
                fontSize = 28.sp,
            )
        }

        Row(modifier = Modifier.fillMaxWidth().background(color=Color(0xFF474C59)).padding(all=13.dp),
            horizontalArrangement = Arrangement.SpaceBetween){
            Text(
                text = "08:00",
                color = Color(0xFFCCC676),
                fontSize = 25.sp
            )
            Text(
                text = "Programiranje",
                color = Color.White,
                fontSize = 25.sp
            )
        }
        Row(modifier = Modifier.fillMaxWidth().background(color=Color(0xFF474C59)).padding(all=13.dp),
            horizontalArrangement = Arrangement.SpaceBetween){
            Text(
                text = "09:00",
                color = Color(0xFFCCC676),
                fontSize = 25.sp
            )
            Text(
                text = "Baze podatkov",
                color = Color.White,
                fontSize = 25.sp
            )
        }
        Row(modifier = Modifier.fillMaxWidth().background(color=Color(0xFF474C59)).padding(all=13.dp),
            horizontalArrangement = Arrangement.SpaceBetween){
            Text(
                text = "10:00",
                color = Color(0xFFCCC676),
                fontSize = 25.sp
            )
            Text(
                text = "Spletne strani",
                color = Color.White,
                fontSize = 25.sp
            )
        }
        Row(modifier = Modifier.fillMaxWidth().background(color=Color(0xFF474C59)).padding(all=13.dp),
            horizontalArrangement = Arrangement.SpaceBetween){
            Text(
                text = "11:00",
                color = Color(0xFFCCC676),
                fontSize = 25.sp
            )
            Text(
                text = "Omrežja",
                color = Color.White,
                fontSize = 25.sp
            )
        }
    }
}

