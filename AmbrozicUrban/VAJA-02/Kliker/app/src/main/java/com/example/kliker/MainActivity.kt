package com.example.kliker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Kliker()
        }
    }
}

private val barvaGumba = Color(0xFFCF7336)
private val barvaStevila = Color.Black

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Kliker() {
    var stevilo by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(barvaGumba)
            .combinedClickable(
                onClick = { stevilo += 1 },
                onLongClick = { stevilo -= 1 }
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stevilo.toString(),
            color = barvaStevila,
            fontSize = 120.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif
        )
    }
}
