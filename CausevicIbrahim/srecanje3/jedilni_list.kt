package com.example.domacanaloga3_2

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
import androidx.compose.ui.text.font.FontWeight
import com.example.domacanaloga3_2.ui.theme.Domacanaloga32Theme

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
            .background(Color(0xFFE0B486))
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Jedilni list",
            color = Color(0xFFDC5F1B),
            fontWeight = FontWeight.SemiBold,
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Pizza margarita", color = Color.Black, fontWeight = FontWeight.SemiBold, fontSize = 20.sp)
            Text("8,50 €", color = Color(0xFF446E24), fontSize = 20.sp)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Testenine", color = Color.Black, fontWeight = FontWeight.SemiBold, fontSize = 20.sp)
            Text("7,20 €", color = Color(0xFF446E24), fontSize = 20.sp)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Solata", color = Color.Black, fontWeight = FontWeight.SemiBold, fontSize = 20.sp)
            Text("5,90 €", color = Color(0xFF446E24), fontSize = 20.sp)
        }
    }
}