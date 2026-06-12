package com.example.am3_5

import android.R.attr.label
import android.R.attr.text
import android.os.Bundle
import android.view.Display
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat.enableEdgeToEdge
import com.example.am3_5.ui.theme.AM3_5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Display()
        }
    }
}

@Composable
fun Display() {
    var a  by remember { mutableStateOf("") }
    var b by remember { mutableStateOf("") }
    var rezultat  by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        OutlinedTextField(
            value = a,
            onValueChange = {a = it},

        )

        OutlinedTextField(
            value = b,
            onValueChange = {b = it},

        )

    }
    Row() {
        Button(onClick = {
            val c = a.toDoubleOrNull() ?: 0.0
            val d = b.toDoubleOrNull() ?: 0.0
            rezultat = (c + d).toString()
        }) {
            Text("+")
        }
        Button(onClick = {
            val c = a.toDoubleOrNull() ?: 0.0
            val d = b.toDoubleOrNull() ?: 0.0
            rezultat = (c - d).toString()
        }) {
            Text("-")
        }
        Button(onClick = {
            val c = a.toDoubleOrNull() ?: 0.0
            val d = b.toDoubleOrNull() ?: 0.0
            rezultat = (c * d).toString()
        }) {
            Text("*")
        }
        Button(onClick = {
            val c = a.toDoubleOrNull() ?: 0.0
            val d = b.toDoubleOrNull() ?: 0.0
            rezultat = if (d !=0.0) (c / d).toString() else "Delenje z 0!"
        }) {
            Text("/")
        }
        RadioButton(onClick = {
            val c = a.toDoubleOrNull() ?: 0.0
            val d = b.toDoubleOrNull() ?: 0.0
            rezultat = (c % c).toString()
        }) {
            Text("%")
        }

        Text("Rezulzat: $rezultat")
    }

}