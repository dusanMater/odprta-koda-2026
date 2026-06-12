package com.example.domacanaloga_igra8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domacanaloga_igra8.ui.theme.Domacanaloga_igra8Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Domacanaloga_igra8Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    IgraKliker()
                }
            }
        }
    }
}

@Composable
fun IgraKliker() {
    var trenutniRezultat by remember { mutableStateOf(0) }
    var obvestilo by remember { mutableStateOf("") }
    var aktivnaIgra by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.Red)
                .clickable(enabled = aktivnaIgra) {
                    trenutniRezultat++
                    if (trenutniRezultat >= 20) {
                        obvestilo = "RDEČI JE ZMAGAL!"
                        aktivnaIgra = false
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            if (!aktivnaIgra && trenutniRezultat >= 20) {
                Text(
                    text = "ZMAGA!",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                value = if (aktivnaIgra) trenutniRezultat.toString() else obvestilo,
                onValueChange = {},
                readOnly = true,
                textStyle = LocalTextStyle.current.copy(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = if (aktivnaIgra) Color.Black else Color(0xFF6750A4)
                ),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .border(2.dp, Color.LightGray, RoundedCornerShape(8.dp)),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )

            if (!aktivnaIgra) {
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = {
                        trenutniRezultat = 0
                        obvestilo = ""
                        aktivnaIgra = true
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
                ) {
                    Text("Ponovni zagon")
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.Blue)
                .clickable(enabled = aktivnaIgra) {
                    trenutniRezultat--
                    if (trenutniRezultat <= -20) {
                        obvestilo = "MODRI JE ZMAGAL!"
                        aktivnaIgra = false
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            if (!aktivnaIgra && trenutniRezultat <= -20) {
                Text(
                    text = "ZMAGA!",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White
                )
            }
        }
    }
}