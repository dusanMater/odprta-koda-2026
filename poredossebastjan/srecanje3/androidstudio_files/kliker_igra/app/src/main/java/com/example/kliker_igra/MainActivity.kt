package com.example.kliker_igra

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Igra()
        }
    }
}

@Composable
fun Igra()
{
    var stevilo by remember { mutableStateOf(0) }
    var konec by remember { mutableStateOf(false) }
    var zmagovalec by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Button(
            onClick = {

                if(!konec)
                {
                    stevilo++

                    if(stevilo >= 20)
                    {
                        konec = true
                        zmagovalec = "Rdeči je zmagal"
                    }
                }
            },

            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),

            shape = RectangleShape
        ) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Red),

                contentAlignment = Alignment.Center
            ) {
                Text("")
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),

            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = stevilo.toString(),
                fontSize = 30.sp
            )

            if(konec)
            {
                Text(
                    text = zmagovalec,
                    fontSize = 20.sp
                )
            }
        }

        Button(
            onClick = {

                if(!konec)
                {
                    stevilo--

                    if(stevilo <= -20)
                    {
                        konec = true
                        zmagovalec = "Modri je zmagal"
                    }
                }
            },

            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),

            shape = RectangleShape
        ) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Blue),

                contentAlignment = Alignment.Center
            ) {
                Text("")
            }
        }
    }
}