package com.example.a04_kvadrant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a04_kvadrant.ui.theme._04KvadrantTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {

                Kvadrant(
                    ozadje = Color(0xFFEADDFF),
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .fillMaxHeight(0.5f)
                        .align(Alignment.TopStart),
                    text1 = "Text composable",
                    text2 = "Displays text and follows the recommended Material Design guidelines."
                )

                Kvadrant(
                    ozadje = Color(0xFFD0BCFF),
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .fillMaxHeight(0.5f)
                        .align(Alignment.TopEnd),
                    text1 = "Image composable",
                    text2 = "Creates a composable that lays out and draws a given Painter class object."
                )

                Kvadrant(
                    ozadje = Color(0xFFB69DF8),
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .fillMaxHeight(0.5f)
                        .align(Alignment.BottomStart),
                    text1 = "Row composable",
                    text2 = "A layout composable that places its children in a horizontal sequence."
                )

                Kvadrant(
                    ozadje = Color(0xFFF6EDFF),
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .fillMaxHeight(0.5f)
                        .align(Alignment.BottomEnd),
                    text1 = "Column composable",
                    text2 = "A layout composable that places its children in a vertical sequence."
                )
            }
        }
    }
}

@Composable
fun Kvadrant(modifier: Modifier = Modifier, ozadje: Color, text1: String, text2: String){
    Box(modifier = modifier.background(ozadje),
        contentAlignment = Alignment.Center){
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(16.dp)){
            Text(text = text1, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 16.dp))
            Text(text = text2)
        }
    }
}