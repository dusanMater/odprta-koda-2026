package com.example.domacanaloga3_kliker
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.domacanaloga3_kliker.ui.theme.Domacanaloga3_klikerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Domacanaloga3_klikerTheme() {
                Kliker()
            }
        }
    }
}

@Composable
fun Kliker() {
    var stevec by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan)
            .clickable {
                stevec++
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stevec.toString(),
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}