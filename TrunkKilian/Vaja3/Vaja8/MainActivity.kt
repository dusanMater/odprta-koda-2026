package com.example.am04

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IgraKliker()
        }
    }
}

@Composable
fun IgraKliker() {
    var rezultat by remember { mutableIntStateOf(0) }
    var zmagovalec by remember { mutableStateOf("") }
    val jeKonec = zmagovalec.isNotEmpty()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .statusBarsPadding()
    ) {
        Text(
            text = "Igrica kdo bo hitrejši",
            color = Color.Black,
            fontSize = 13.sp,
            modifier = Modifier.padding(start = 4.dp, bottom = 4.dp)
        )
        IgralecGumb(
            barva = Color.Red,
            enabled = !jeKonec,
            modifier = Modifier.weight(1f)
        ) {
            rezultat++
            if (rezultat >= 20) {
                zmagovalec = "Zmagal je rdeči igralec"
            }
        }
        TextField(
            value = if (jeKonec) zmagovalec else rezultat.toString(),
            onValueChange = {},
            readOnly = true,
            singleLine = true,
            textStyle = androidx.compose.ui.text.TextStyle(
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedIndicatorColor = Color.LightGray,
                unfocusedIndicatorColor = Color.LightGray
            ),
            modifier = Modifier.fillMaxWidth()
        )
        IgralecGumb(
            barva = Color.Blue,
            enabled = !jeKonec,
            modifier = Modifier.weight(1f)
        ) {
            rezultat--
            if (rezultat <= -20) {
                zmagovalec = "Zmagal je modri igralec"
            }
        }
    }
}

@Composable
fun IgralecGumb(
    barva: Color,
    enabled: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(0.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = barva,
            disabledContainerColor = barva,
            contentColor = Color.Transparent,
            disabledContentColor = Color.Transparent
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 2.dp)
    ) {
        Box {}
    }
}
