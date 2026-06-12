package com.example.am7_6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.am7_6.ui.theme.AM7_6Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AM7_6Theme {
                Display()
            }
        }
    }
}

@Composable
fun Display() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFD2E8D4)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "Android Logo",
                    modifier = Modifier
                        .size(120.dp)
                        .background(color = Color(0xFF073042))
                )
                Text("Jennifer Doe", fontSize = 40.sp)
                Text(
                    "Android Developer Extraordinaire",
                    color = Color(0xFF3DDC84),
                    fontWeight = Bold,
                    modifier = Modifier.padding(top = 5.dp)
                )
            }
        }

        Column(modifier = Modifier.padding(bottom = 32.dp)) {
            Text("+11 (123) 444 555 666", modifier = Modifier.padding(5.dp))
            Text("@AndroidDev", modifier = Modifier.padding(5.dp))
            Text("jen.doe@android.com", modifier = Modifier.padding(5.dp))
        }
    }
}