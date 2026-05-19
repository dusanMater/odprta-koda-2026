package com.example.klikerigra

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.klikerigra.ui.theme.KlikerIgraTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Alignment


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
           KlikerIgra()
        }
    }
}

@Composable
fun KlikerIgra(modifier: Modifier = Modifier) {
    var stevilo by remember { mutableIntStateOf(0) }

    val konec = stevilo >= 20 || stevilo <= -20

    val prikaz = if (stevilo >= 20) {
        "Prvi igralec je zmagal"
    } else if (stevilo <= -20) {
        "Drugi igralec je zmagal"
    } else {
        stevilo.toString()
    }

    Column(modifier = Modifier.fillMaxSize()){
        Row(modifier = Modifier.fillMaxWidth().weight(1f)){
            Button(
                modifier = Modifier.fillMaxSize(),
                shape = RoundedCornerShape(0.dp),
                onClick = { stevilo++ },
                enabled = !konec,
            ){Text("")}
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
            Text(text = prikaz, fontSize = 40.sp)
        }
        Row(modifier = Modifier.fillMaxWidth().weight(1f)){
            Button(
                modifier = Modifier.fillMaxSize(),
                shape = RoundedCornerShape(0.dp),
                onClick = { stevilo-- },
                enabled = !konec,
            ){Text("")}
        }
    }
}

