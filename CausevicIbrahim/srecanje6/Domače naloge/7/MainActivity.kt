package com.example.domacanaloga_6_7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
                KockaZaslon()
            }
        }
    }
}

@Composable
fun KockaZaslon() {
    var rezultatKocke by remember { mutableStateOf(1) }

    val slikaKocke = when (rezultatKocke) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = slikaKocke),
            contentDescription = "Kocka s številko $rezultatKocke",
            modifier = Modifier.size(200.dp)
        )

        Spacer(modifier = Modifier.height(25.dp))

        Button(
            onClick = { rezultatKocke = (1..6).random() },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2196F3)
            ),
            contentPadding = PaddingValues(horizontal = 25.dp, vertical = 12.dp)
        ) {
            Text(
                text = "Roll",
                fontSize = 20.sp,
                color = Color.White
            )
        }
    }
}