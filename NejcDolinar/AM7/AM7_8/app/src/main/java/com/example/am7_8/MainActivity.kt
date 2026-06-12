package com.example.am7_8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.am7_8.ui.theme.AM7_8Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AM7_8Theme {
                Display()
            }
        }
    }
}

@Composable
fun Display() {
    var step by remember { mutableIntStateOf(1) }
    var squeezeCount by remember { mutableIntStateOf((2..4).random()) }
    var tapsRequired by remember { mutableIntStateOf(squeezeCount) }

    val image = when (step) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val text = when (step) {
        1 -> "Tap the lemon tree to select a lemon"
        2 -> "Keep tapping the lemon to squeeze it"
        3 -> "Tap the lemonade to drink it"
        else -> "Tap the empty glass to start again"
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            when (step) {
                1 -> {
                    step = 2
                    tapsRequired = (2..4).random() // new random squeeze count each time
                }
                2 -> {
                    tapsRequired--
                    if (tapsRequired == 0) step = 3 // only advance when fully squeezed
                }
                3 -> step = 4
                4 -> step = 1
            }
        }) {
            Image(
                painter = painterResource(image),
                contentDescription = text
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text)
    }
}