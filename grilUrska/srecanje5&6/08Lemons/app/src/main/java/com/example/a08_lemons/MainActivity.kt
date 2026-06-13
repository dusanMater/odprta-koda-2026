package com.example.a08_lemons

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.a08_lemons.ui.theme._08LemonsTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            LemonadeApp()
        }
    }
}

@Composable
fun LemonadeApp() {
    var korak by remember { mutableStateOf(1) }
    var stiskanja by remember { mutableStateOf(0) }
    var potrebnaStiskanja by remember { mutableStateOf(0) }

    var slika = R.drawable.lemon_tree
    var besedilo = "Tap the lemon tree to select a lemon"

    if (korak == 1) {
        slika = R.drawable.lemon_tree
        besedilo = "Tapni na drevo, da izbereš limono"
    } else if (korak == 2) {
        slika = R.drawable.lemon_squeeze
        besedilo = "Tapkaj limono, da jo stisneš"
    } else if (korak == 3) {
        slika = R.drawable.lemon_drink
        besedilo = "Tapni limonado, da jo spiješ"
    } else {
        slika = R.drawable.lemon_restart
        besedilo = "Tapno kozarec, da začneš znova"
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = besedilo,
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (korak == 1) {
                    korak = 2
                    stiskanja = 0
                    potrebnaStiskanja = (2..4).random()
                } else if (korak == 2) {
                    stiskanja++

                    if (stiskanja >= potrebnaStiskanja) {
                        korak = 3
                    }
                } else if (korak == 3) {
                    korak = 4
                } else {
                    korak = 1
                }
            },
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Yellow
            )
        ) {
            Image(
                painter = painterResource(slika),
                contentDescription = null,
                modifier = Modifier.size(200.dp)
            )
        }
    }
}