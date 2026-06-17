package com.example.predavanje2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.compose.runtime.Composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.Text

import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            Glava()
        }
    }
}

@Composable
fun Glava() {
    Column(
        modifier = Modifier.padding(
            start = 50.dp,
            top = 70.dp
        )
    ) {
        Text(
            text = "Prva vaja za Jetpack Compose",
            modifier = Modifier.padding(bottom = 50.dp)
        )
        Text("Alma Mater")
        Text("Informacijske tehnologije")
    }
}