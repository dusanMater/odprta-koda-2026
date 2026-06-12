package com.example.domaca3

import android.os.Bundle
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import com.example.domaca3.ui.theme.Domaca3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Domaca3Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    JedilniList(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun JedilniList(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFfff0db))
            .padding(16.dp)
    ) {
        Text(
            text = "Jedilni list",
            fontSize = 36.sp,
            color = Color.Cyan
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Pizza", color = Color.Black, fontSize = 24.sp)
            Text("8€", color = Color.Green, fontSize = 24.sp)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Burger", color = Color.Black, fontSize = 24.sp)
            Text("6€", color = Color.Green , fontSize = 24.sp)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Pasta", color = Color.Black, fontSize = 24.sp)
            Text("7€", color = Color.Green, fontSize = 24.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DNPreview() {
    Domaca3Theme {
        JedilniList()
    }
}