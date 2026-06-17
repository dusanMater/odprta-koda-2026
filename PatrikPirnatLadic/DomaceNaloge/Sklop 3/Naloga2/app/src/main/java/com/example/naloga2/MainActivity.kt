package com.example.naloga2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
            Display()
        }
    }
}

@Composable
fun Display() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 24.dp, top = 48.dp, end = 24.dp)
    ) {
        Text("Jedilni list", color = Color(0xFF3F51B5), fontSize = 24.sp)

        Spacer(modifier = Modifier.height(18.dp))

        MenuRow(name = "Pizza margarita", price = "8,50 €")
        MenuRow(name = "Testenine", price = "7,20 €")
        MenuRow(name = "Solata", price = "5,90 €")
    }
}

@Composable
fun MenuRow(name: String, price: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(name, color = Color.Black, fontSize = 15.sp)
        Text(price, color = Color(0xFF188C3A), fontSize = 15.sp)
    }
}
