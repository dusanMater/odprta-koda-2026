package com.example.domacanaloga_6_8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                LimonadaAplikacija()
            }
        }
    }
}

@Composable
fun LimonadaAplikacija() {
    var trenutniKorak by remember { mutableStateOf(1) }

    var stiskanje by remember { mutableStateOf((2..4).random()) }
    var trenutnoStiskanje by remember { mutableStateOf(0) }

    val (slika, besedilo) = when (trenutniKorak) {
        1 -> Pair(R.drawable.lemon_tree, "Tap the lemon tree to select a lemon")
        2 -> Pair(R.drawable.lemon_squeeze, "Keep tapping the lemon to squeeze it")
        3 -> Pair(R.drawable.lemon_drink, "Tap the lemonade to drink it")
        else -> Pair(R.drawable.lemon_restart, "Tap the empty glass to start again")
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize().padding(20.dp)
    ) {
        Text(
            text = besedilo,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .size(220.dp)
                .background(
                    color = Color(0xFFC3ECD2),
                    shape = RoundedCornerShape(24.dp)
                )
                .clickable {
                    when (trenutniKorak) {
                        1 -> {
                            stiskanje = (2..4).random()
                            trenutnoStiskanje = 0
                            trenutniKorak = 2
                        }
                        2 -> {
                            trenutnoStiskanje++
                            if (trenutnoStiskanje >= stiskanje) {
                                trenutniKorak = 3
                            }
                        }
                        3 -> {
                            trenutniKorak = 4
                        }
                        4 -> {
                            trenutniKorak = 1
                        }
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = slika),
                contentDescription = null,
                modifier = Modifier.size(160.dp)
            )
        }
    }
}