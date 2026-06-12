package com.example.hellow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.hellow.ui.theme.HellowTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OsebnaVizitka()
        }
            }
        }

@Composable
fun OsebnaVizitka() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFBBDEFB))
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(18.dp))
        Text(
            text = "Ibrahim Čaušević",
            color = Color.Blue,
            fontSize = 30.sp

        )

        Text(
            text = "študent",
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(18.dp))
        Text(
            text = "Univerza Alma Mater Europaea",
            color = Color.Red,
            fontSize = 25.sp
        )
        Spacer(modifier = Modifier.height(18.dp))
        Text(
            text = "ibrahim.causevic@almamater.com",
            fontSize = 20.sp
        )
    }
}
