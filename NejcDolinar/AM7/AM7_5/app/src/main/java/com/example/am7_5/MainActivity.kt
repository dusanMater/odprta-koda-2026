package com.example.am7_5

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.am7_5.ui.theme.AM7_5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AM7_5Theme {
                Display()
            }
        }
    }
}

@Composable
fun Robot(barva: Color, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.ic_android),
        contentDescription = "Robot",
        colorFilter = ColorFilter.tint(barva),
        modifier = modifier.size(50.dp)
    )
}

@Composable
fun Display() {
    Box(modifier = Modifier.fillMaxSize()) {
        // top row - red, green, red
        Row(modifier = Modifier.align(Alignment.TopStart)) {
            Robot(barva = Color.Red)
            Robot(barva = Color.Green)
            Robot(barva = Color.Red)
        }
        // lone green one bottom right
        Robot(
            barva = Color.Green,
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}

