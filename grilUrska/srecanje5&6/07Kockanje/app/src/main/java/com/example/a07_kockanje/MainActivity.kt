package com.example.a07_kockanje

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.a07_kockanje.ui.theme._07KockanjeTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Kockanje()
        }
    }
}

@Composable
fun Kockanje() {

    var stevilka by remember { mutableStateOf(1) }

    var slika = R.drawable.one

    if (stevilka == 1) {
        slika = R.drawable.one
    } else if (stevilka == 2) {
        slika = R.drawable.two
    } else if (stevilka == 3) {
        slika = R.drawable.three
    } else if (stevilka == 4) {
        slika = R.drawable.four
    } else if (stevilka == 5) {
        slika = R.drawable.five
    } else {
        slika = R.drawable.six
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Kocka(slika)

        Spacer(
            modifier = Modifier.height(32.dp)
        )

        Button(
            onClick = {
                stevilka = (1..6).random()
            }
        ) {
            Text("Roll")
        }
    }
}

@Composable
fun Kocka(slika: Int) {

    Image(
        painter = painterResource(slika),
        contentDescription = null,
        modifier = Modifier.size(200.dp)
    )
}