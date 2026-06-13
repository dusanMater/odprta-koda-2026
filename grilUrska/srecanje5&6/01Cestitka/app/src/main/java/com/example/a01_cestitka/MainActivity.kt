package com.example.a01_cestitka

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.a01_cestitka.ui.theme._01CestitkaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Cestitka(imeNaslovljenca = "Urska", imePosiljatelja = "Klara")
        }
    }
}

@Composable
fun Cestitka(
    modifier: Modifier = Modifier,
    imeNaslovljenca: String,
    imePosiljatelja: String
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.ozadje),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )

        Besedilo(
            imeNaslovljenca = imeNaslovljenca,
            imePosiljatelja = imePosiljatelja,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun Besedilo(
    modifier: Modifier = Modifier,
    imeNaslovljenca: String,
    imePosiljatelja: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Vse najboljše dragi/a $imeNaslovljenca",
            fontSize = 76.sp
        )

        Text(
            text = imePosiljatelja,
            fontSize = 36.sp
        )
    }
}
