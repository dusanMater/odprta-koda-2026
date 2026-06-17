package com.example.naloga5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.naloga5.ui.theme.Naloga5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Naloga5Theme {
                Display()
            }
        }
    }
}

@Composable
fun Robot(barva: Color, modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.size(28.dp)) {
        val w = size.width
        val h = size.height
        drawRect(
            color = barva,
            topLeft = androidx.compose.ui.geometry.Offset(w * 0.18f, h * 0.42f),
            size = androidx.compose.ui.geometry.Size(w * 0.64f, h * 0.38f)
        )
        drawArc(
            color = barva,
            startAngle = 180f,
            sweepAngle = 180f,
            useCenter = true,
            topLeft = androidx.compose.ui.geometry.Offset(w * 0.18f, h * 0.18f),
            size = androidx.compose.ui.geometry.Size(w * 0.64f, h * 0.48f)
        )
        drawCircle(Color.White, radius = w * 0.04f, center = androidx.compose.ui.geometry.Offset(w * 0.38f, h * 0.38f))
        drawCircle(Color.White, radius = w * 0.04f, center = androidx.compose.ui.geometry.Offset(w * 0.62f, h * 0.38f))
        drawRect(barva, topLeft = androidx.compose.ui.geometry.Offset(w * 0.08f, h * 0.46f), size = androidx.compose.ui.geometry.Size(w * 0.08f, h * 0.26f))
        drawRect(barva, topLeft = androidx.compose.ui.geometry.Offset(w * 0.84f, h * 0.46f), size = androidx.compose.ui.geometry.Size(w * 0.08f, h * 0.26f))
        drawLine(barva, androidx.compose.ui.geometry.Offset(w * 0.32f, h * 0.2f), androidx.compose.ui.geometry.Offset(w * 0.22f, h * 0.04f), strokeWidth = w * 0.05f)
        drawLine(barva, androidx.compose.ui.geometry.Offset(w * 0.68f, h * 0.2f), androidx.compose.ui.geometry.Offset(w * 0.78f, h * 0.04f), strokeWidth = w * 0.05f)
    }
}

@Composable
fun Display() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(top = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 6.dp, top = 6.dp)
        ) {
            Robot(barva = Color.Red)
            Robot(barva = Color.Green, modifier = Modifier.padding(start = 36.dp))
            Robot(barva = Color.Red, modifier = Modifier.padding(start = 36.dp))
        }
        Robot(
            barva = Color.Green,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 180.dp, end = 6.dp)
        )
    }
}
