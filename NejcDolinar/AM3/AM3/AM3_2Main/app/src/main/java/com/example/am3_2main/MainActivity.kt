package com.example.am3_2

import android.os.Bundle
import android.view.Display
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
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
        modifier = Modifier.fillMaxSize()
    ) {
        Text("Jedilni List", color = Color.Green, fontSize = 25.sp)

        Spacer(modifier = Modifier.height(5.dp))

        Column(
            modifier = Modifier.padding(top = 3.dp)
        ) {
            Row {
                Text("Pizza margarita")
                Text("8,50", color = Color.Green)
            }
            Row {
                Text("Testenine")
                Text("7,20", color = Color.Green)
            }
            Row {
                Text("Solata")
                Text("5,90", color = Color.Green)
            }
        }
    }
}
