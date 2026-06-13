package com.example.a05_aliens

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.a05_aliens.ui.theme._05AliensTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Box(
                modifier = Modifier.fillMaxSize().statusBarsPadding()
            ) {

                Robotek(
                    barva = Color.Red,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                )

                Robotek(
                    barva = Color.Green,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                )

                Robotek(
                    barva = Color.Red,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                )

                Robotek(
                    barva = Color.Green,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                )

                Robotek(
                    barva = Color.Red,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                )
            }
        }
    }
}

@Composable
fun Robotek(
    barva: Color,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(R.drawable.alien),
        contentDescription = null,
        colorFilter = ColorFilter.tint(barva),
        modifier = modifier
    )
}