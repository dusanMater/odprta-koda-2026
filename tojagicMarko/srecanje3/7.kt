package com.example.domaca3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.unit.sp
import com.example.domaca3.ui.theme.Domaca3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Domaca3Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    KlikerCelZaslon(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun KlikerCelZaslon(modifier: Modifier = Modifier) {
    var stevec by remember { mutableStateOf(0) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Cyan)
            .clickable { stevec++ },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stevec.toString(),
            fontSize = 48.sp,
            color = Color.Black
        )
    }
}