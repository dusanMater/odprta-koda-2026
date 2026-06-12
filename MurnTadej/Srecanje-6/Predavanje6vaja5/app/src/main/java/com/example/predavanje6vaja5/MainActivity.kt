package com.example.predavanje6vaja5

import android.R.attr.name
import android.os.Bundle
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Color
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.predavanje6vaja5.ui.theme.Predavanje6vaja5Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Zaslon()
        }
    }
}

@Composable
fun Robotek(barva: Color, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.alien),
        contentDescription = "Robotek",
        colorFilter = ColorFilter.tint(barva),
        modifier = modifier
    )
}

@Composable
fun Zaslon() {
    Column {
        Robotek(Color.Green)
        Robotek(Color.Blue)
        Robotek(Color.Red)
    }
}