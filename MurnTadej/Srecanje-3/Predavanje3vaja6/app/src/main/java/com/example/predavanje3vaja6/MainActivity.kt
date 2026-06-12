package com.example.predavanje3vaja6

import android.R.attr.button
import android.R.attr.onClick
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.predavanje3vaja6.ui.theme.Predavanje3vaja6Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Kliker()
        }
    }
}



@Composable
fun Kliker() {
    var klik by remember { mutableStateOf(0) }   // Int, začne pri 0

    Button(
        onClick = { klik++ },                       // ob kliku: poveča število
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF4CAF50),   // barva OZADJA gumba
            contentColor = Color.White            // barva BESEDILA na gumbu
        ),
        shape = RectangleShape,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "$klik",
            fontSize = 40.sp
        )
    }
}