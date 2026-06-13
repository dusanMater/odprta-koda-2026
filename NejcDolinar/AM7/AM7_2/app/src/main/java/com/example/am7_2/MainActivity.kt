package com.example.am7_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Display(
                Main = "Jetpack Compose tutorial",
                Sub = "loren .............."
            )
        }
    }
}

@Composable
fun Display(Main: String, Sub: String) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .paint(
                    painterResource(R.drawable.glava),
                )
        ) {}
        Main(Main = Main, modifier = Modifier.padding(16.dp))
        Sub(Sub = Sub, modifier = Modifier.padding(16.dp))
    }
}

@Composable
fun Main(Main: String, modifier: Modifier = Modifier) {
    Text(
        text = Main,
        fontSize = 24.sp,
        modifier = modifier  // actually use it
    )
}

@Composable
fun Sub(Sub: String, modifier: Modifier = Modifier) {
    Text(
        text = Sub,
        fontSize = 24.sp,
        textAlign = TextAlign.Justify,
        modifier = modifier  // actually use it
    )
}