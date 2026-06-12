package com.example.kalkulatornapredni

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.kalkulatornapredni.ui.theme.KalkulatorNapredniTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material3.ButtonDefaults

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KalkulatorNapredni()
        }
    }
}

@Composable
fun KalkulatorNapredni(modifier: Modifier = Modifier) {

    var st1 by remember { mutableStateOf("") }
    var st2 by remember { mutableStateOf("") }
    var rez by remember { mutableStateOf("") }

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

            Button(
                modifier = Modifier.weight(1f),
                onClick = {
                    var s1: Int = st1.toInt()
                    var s2: Int = st2.toInt()
                    var r: Int = s1 + s2
                    rez = r.toString()
                },

                shape = RoundedCornerShape(12.dp),

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF9322F5),
                    contentColor = Color.White
                )

            ) {
                Text("+")
            }
            Button(
                modifier = Modifier.weight(1f),
                onClick = {
                    var s1: Int = st1.toInt()
                    var s2: Int = st2.toInt()
                    var r: Int = s1 - s2
                    rez = r.toString()
                },

                shape = RoundedCornerShape(12.dp),

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF9322F5),
                    contentColor = Color.White
                )

            ) {
                Text("-")
            }
            Button(
                modifier = Modifier.weight(1f),
                onClick = {
                    var s1: Int = st1.toInt()
                    var s2: Int = st2.toInt()
                    var r: Int = s1 * s2
                    rez = r.toString()
                },

                shape = RoundedCornerShape(12.dp),

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF9322F5),
                    contentColor = Color.White
                )

            ) {
                Text("*")
            }
            Button(
                modifier = Modifier.weight(1f),
                onClick = {
                    var s1: Double = st1.toDouble()
                    var s2: Double = st2.toDouble()
                    var r: Double = s1 / s2
                    rez = r.toString()
                },

                shape = RoundedCornerShape(12.dp),

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF9322F5),
                    contentColor = Color.White
                )

            ) {
                Text("/")
            }
            Button(
                modifier = Modifier.weight(1f),
                onClick = {
                    var s1: Int = st1.toInt()
                    var s2: Int = st2.toInt()
                    var r: Int = s1 % s2
                    rez = r.toString()
                },

                shape = RoundedCornerShape(12.dp),

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF9322F5),
                    contentColor = Color.White
                )

            ) {
                Text("%")
            }
        }

        OutlinedTextField(
            value = rez,
            onValueChange = { rez = it },

            placeholder = {
                Text("Rezultat")
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
    }
}
