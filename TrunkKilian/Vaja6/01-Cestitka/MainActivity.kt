package com.example.am04

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Cestitka()
        }
    }
}

@Composable
fun Cestitka() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.ozadje),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        BesediloCestitke(
            slavljenec = "Miha",
            posiljatelj = "Kilian",
            modifier = Modifier.padding(horizontal = 20.dp)
        )
    }
}

@Composable
fun BesediloCestitke(
    slavljenec: String,
    posiljatelj: String,
    modifier: Modifier
) {
    androidx.compose.foundation.layout.Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = "Vse najboljše dragi $slavljenec",
            fontSize = 76.sp,
            textAlign = TextAlign.Center,
            lineHeight = 82.sp
        )
        Text(
            text = posiljatelj,
            fontSize = 36.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 40.dp)
        )
    }
}
