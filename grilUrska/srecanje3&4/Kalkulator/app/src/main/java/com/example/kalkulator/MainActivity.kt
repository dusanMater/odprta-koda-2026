package com.example.kalkulator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Kalkulator()


        }
    }
}
@Composable
fun Kalkulator() {

    var st1 by remember {mutableStateOf ("")}
    var st2 by remember {mutableStateOf ("")}
    var rez by remember {mutableStateOf ("")}


    Column{
        Spacer(
            modifier = Modifier.height(32.dp)
        )
        Text(text = "Naslov", fontSize = 24.sp)

        Text("Stevilo 1:")
        TextField(
            value = st1,
            onValueChange = {st1 = it},
            modifier = Modifier.fillMaxWidth()
        )

        Text("Stevilo 2:")
        TextField(
            value = st2,
            onValueChange = {st2 = it},
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                var s1:Int = st1.toInt()
                var s2:Int = st2.toInt()
                var r:Int = s1 + s2
                rez = r.toString()
            }
        ) {Text("Seštej")}
        TextField(
            value = rez,
            onValueChange = {st1 = it},
            modifier = Modifier.fillMaxWidth()
        )
    }

}