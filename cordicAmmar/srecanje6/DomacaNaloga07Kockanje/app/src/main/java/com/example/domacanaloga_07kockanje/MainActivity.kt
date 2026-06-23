package com.example.domacanaloga_07kockanje

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.domacanaloga_07kockanje.ui.theme.DomacaNaloga07KockanjeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var number: Number by remember { mutableStateOf(1) }
            Column (
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = when (number) {
                        1 -> painterResource(id = R.drawable.dice_1)
                        2 -> painterResource(id = R.drawable.dice_2)
                        3 -> painterResource(id = R.drawable.dice_3)
                        4 -> painterResource(id = R.drawable.dice_4)
                        5 -> painterResource(id = R.drawable.dice_5)
                        6 -> painterResource(id = R.drawable.dice_6)
                        else -> painterResource(id = R.drawable.dice_1) // Default case
                    },
                    contentDescription = "Kocka broj $number",
                    modifier = Modifier.padding(16.dp)
                )

                Button (
                    onClick = { number = rollDice() },
                    modifier = Modifier.padding(16.dp),
                ) {
                    Text(
                        text = "Roll"
                    )
                }
            }
        }
    }
}

fun rollDice(): Number {
    return (1..6).random()
}