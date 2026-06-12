package com.example.kalkulatornapredni2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kalkulatornapredni2.ui.theme.KalkulatorNapredni2Theme
import androidx.compose.material3.RadioButton
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KalkulatorNapredni2()
        }
    }
}

@Composable
fun KalkulatorNapredni2(modifier: Modifier = Modifier) {
    var st1 by remember { mutableStateOf("") }
    var st2 by remember { mutableStateOf("") }
    var rez by remember { mutableStateOf("") }
    var izbrano by remember { mutableStateOf("Plus") }

    Column(
        modifier = Modifier
            .padding(top = 50.dp)
            .fillMaxWidth()
    ) {

        OutlinedTextField(
            value = st1,
            onValueChange = { st1 = it },

            placeholder = {
                Text("Prvo število")
            },

            shape = RoundedCornerShape(12.dp),

            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = Color.Gray,

                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),

            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = st2,
            onValueChange = { st2 = it },

            placeholder = {
                Text("Drugo število")
            },

            shape = RoundedCornerShape(12.dp),

            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = Color.Gray,

                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),

            modifier = Modifier.fillMaxWidth()
        )

        Row(modifier = Modifier.fillMaxWidth()) {

            RadioButton(
                modifier = Modifier.weight(1f),
                selected = izbrano == "Plus",
                onClick = { izbrano = "Plus" }
            )

            Text("+")

            RadioButton(
                modifier = Modifier.weight(1f),
                selected = izbrano == "Minus",
                onClick = { izbrano = "Minus" }
            )

            Text("-")

            RadioButton(
                modifier = Modifier.weight(1f),
                selected = izbrano == "Krat",
                onClick = { izbrano = "Krat" }
            )

            Text("*")

            RadioButton(
                modifier = Modifier.weight(1f),
                selected = izbrano == "Deljeno",
                onClick = { izbrano = "Deljeno" }
            )

            Text("/")

            RadioButton(
                modifier = Modifier.weight(1f),
                selected = izbrano == "Modulo",
                onClick = { izbrano = "Modulo" }
            )

            Text("%")

        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                if(izbrano == "Plus"){
                    var s1: Int = st1.toInt()
                    var s2: Int = st2.toInt()
                    var r: Int = s1 + s2
                    rez = r.toString()
                } else if(izbrano == "Minus"){
                    var s1: Int = st1.toInt()
                    var s2: Int = st2.toInt()
                    var r: Int = s1 - s2
                    rez = r.toString()
                } else if(izbrano == "Krat"){
                    var s1: Int = st1.toInt()
                    var s2: Int = st2.toInt()
                    var r: Int = s1 * s2
                    rez = r.toString()
                } else if(izbrano == "Deljeno"){
                    var s1: Double = st1.toDouble()
                    var s2: Double = st2.toDouble()
                    var r: Double = s1 / s2
                    rez = r.toString()
                } else if(izbrano == "Modulo"){
                    var s1: Int = st1.toInt()
                    var s2: Int = st2.toInt()
                    var r: Int = s1 % s2
                    rez = r.toString()
                }
            }
        ) {
            Text("Izračunaj")
        }
        Text(text = "Rezultat: " + rez,
            fontSize= 20.sp,
            modifier = Modifier.padding(top=15.dp, start=15.dp))
    }
}