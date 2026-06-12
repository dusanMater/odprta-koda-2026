package com.example.domaca_naloga_vaja_8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domaca_naloga_vaja_8.ui.theme.Domaca_naloga_vaja_8Theme

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

    var score by remember {
        mutableStateOf(0)
    }

    var winner by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Button(
            onClick = {

                if(winner==""){
                    score++

                    if(score>=20){
                        winner="Red"
                    }
                }

            },
            modifier = Modifier.weight(1f).fillMaxWidth(),
            shape = RoundedCornerShape(0.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red,
                contentColor = Color.White
            )
        ) {}

        Text(
            text = "Score: $score",
            fontSize = 30.sp,
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        if (winner.isNotEmpty()) {

            Text(
                text = "$winner wins!",
                fontSize = 30.sp
            )
        }

        Button(
            onClick = {

                if(winner==""){
                    score--

                    if(score<=-20){
                        winner="Blue"
                    }
                }

            },
            modifier = Modifier.weight(1f).fillMaxWidth(),
            shape = RoundedCornerShape(0.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue,
                contentColor = Color.White
            )
        ) {}
    }
}