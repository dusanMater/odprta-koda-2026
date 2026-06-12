package com.example.domaca3

import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.domaca3.ui.theme.Domaca3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Domaca3Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Urnik(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Urnik(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Moj urnik",
            color = Color.White,
            fontSize = 26.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(vertical = 8.dp)
                .background(Color.Gray)
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("8:00", color = Color.Yellow)
            Text("Matematika", color = Color.White)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(vertical = 8.dp)
                .background(Color.Gray)
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("9:00", color = Color.Yellow)
            Text("Slovenščina", color = Color.White)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(vertical = 8.dp)
                .background(Color.Gray)
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("10:00", color = Color.Yellow)
            Text("Programiranje", color = Color.White)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(vertical = 8.dp)
                .background(Color.Gray)
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("11:00", color = Color.Yellow)
            Text("Angleščina", color = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DNPreview() {
    Domaca3Theme {
        Urnik()
    }
}