package com.example.predavanje3vaja4

import android.R.attr.text
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.SemanticsActions.OnClick
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.predavanje3vaja4.ui.theme.Predavanje3vaja4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
                Kalkulator()
            }
        }
    }

@Composable
fun Kalkulator() {
    // 1) STATE — tri "škatle", ki jih Compose opazuje
    var st1 by remember { mutableStateOf("") }
    var st2 by remember { mutableStateOf("") }
    var vsota by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Naslov",
            fontSize = 24.sp
        )

        Spacer(Modifier.height(12.dp))

        Text("Število 1:")
        TextField(
            value = st1,
            onValueChange = { st1 = it }
        )

        Spacer(Modifier.height(12.dp))

        Text("Število 2:")
        TextField(
            value = st2,
            onValueChange = { st2 = it }
        )

        Spacer(Modifier.height(12.dp))

        // 2) onClick je LAMBDA { } — koda se izvede ob kliku
        Button(onClick = {
            val a = st1.toInt()   // 3) String -> Int
            val b = st2.toInt()
            vsota = a + b          // zdaj je to pravo seštevanje
        }) {
            Text("Seštej")
        }

        Spacer(Modifier.height(12.dp))

        Text("Vsota je: $vsota")
    }
}