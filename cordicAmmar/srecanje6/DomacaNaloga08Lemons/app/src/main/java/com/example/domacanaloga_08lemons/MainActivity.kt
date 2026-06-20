package com.example.domacanaloga_08lemons

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domacanaloga_08lemons.ui.theme.DomacaNaloga08LemonsTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lemons()
        }
    }
}

@Composable
fun Lemons() {

    var step by remember { mutableIntStateOf(1) }

    var squeezeCount by remember {
        mutableIntStateOf(Random.nextInt(2, 5))
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        val imageRes: Int
        val text: String

        when (step) {
            1 -> {
                imageRes = R.drawable.lemon_tree
                text = "Tapni limonino drevo, da izbereš limono."
            }
            2 -> {
                imageRes = R.drawable.lemon_squeeze
                text = "Tapkaj limono, da narediš limonado."
            }
            3 -> {
                imageRes = R.drawable.lemon_drink
                text = "Tapni limonado, da jo spiješ."
            }
            else -> {
                imageRes = R.drawable.lemon_restart
                text = "Tapni prazen kozarec za nov začetek."
            }
        }

        Image(
            painter = painterResource(imageRes),
            contentDescription = null,
            modifier = Modifier
                .padding(24.dp)
                .clickable {
                    when (step) {
                        1 -> {
                            step = 2
                            squeezeCount = Random.nextInt(2, 5)
                        }
                        2 -> {
                            squeezeCount--

                            if (squeezeCount == 0) {
                                step = 3
                            }
                        }
                        3 -> {
                            step = 4
                        }
                        4 -> {
                            step = 1
                        }
                    }
                }
                .background(Color.Yellow, shape = RoundedCornerShape(24.dp))
                .border(2.dp, Color.Red, RoundedCornerShape(24.dp))
        )

        Text(
            text = text,
            fontSize = 18.sp
        )
    }
}