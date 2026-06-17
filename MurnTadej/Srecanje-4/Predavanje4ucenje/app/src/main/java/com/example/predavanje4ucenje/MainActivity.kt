package com.example.predavanje4ucenje

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

data class Avto(
    val znamka: String,
    val prevozenikm: Int,
    val stlastnik: Int
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            avtomobili()
        }
    }
}

@Composable
fun avtomobili() {

    val avti = remember {
        mutableStateListOf(
            Avto("Ferrari", 123000, 2),
            Avto("Maserati", 18700, 1),
            Avto("Lamborghini", 150461, 4)
        )
    }

    var znamka by remember { mutableStateOf("") }

    Column {


        Spacer(Modifier.height(60.dp))


        TextField(
            value = znamka,
            onValueChange = { znamka = it }
        )

        Button(onClick = {
            avti.add(Avto(znamka, 0, 1))
            znamka = ""
        }) {
            Text("Dodaj avto")
        }

        LazyColumn {
            items(avti) { avto ->
                Row {
                    Image(
                        painter = painterResource(R.drawable.avto),
                        contentDescription = "Avto",
                        modifier = Modifier.size(80.dp)
                    )
                    Text(avto.znamka)
                }
            }
        }
    }
}