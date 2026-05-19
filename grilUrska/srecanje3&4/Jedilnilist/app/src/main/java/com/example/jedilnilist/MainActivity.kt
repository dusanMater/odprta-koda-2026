package com.example.jedilnilist

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
import com.example.jedilnilist.ui.theme.JedilniListTheme

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
fun JedilniList(modifier: Modifier = Modifier) {
    Column (modifier = Modifier
        .fillMaxSize()
        .background(color = Color(0xFFE0DCB8))
        .padding(top=50.dp, start=20.dp, end=20.dp)) {
        Text(
            text = "Jedilni list",
            fontSize = 25.sp,
            color = Color.Red,
            modifier = Modifier.padding(bottom=30.dp)
        )

        Row (modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = "Pizza margarita",
                fontSize = 15.sp
            )
            Text(
                text = "8,50 €",
                fontSize = 15.sp,
                color = Color(0xFF167814)
            )
        }
        Row (modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = "Testenine",
                fontSize = 15.sp
            )
            Text(
                text = "7,20 €",
                fontSize = 15.sp,
                color = Color(0xFF167814)
            )
        }
        Row (modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = "Solata",
                fontSize = 15.sp
            )
            Text(
                text = "5,90 €",
                fontSize = 15.sp,
                color = Color(0xFF167814)
            )
        }
    }
}
