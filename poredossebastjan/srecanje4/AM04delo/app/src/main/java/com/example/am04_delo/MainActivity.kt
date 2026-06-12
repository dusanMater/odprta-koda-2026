package com.example.am04_delo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import model.Oseba

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            Column(
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(12.dp)
            ) {

                PrikazFotografij()
            }
        }
    }
}

@Composable
fun PrikazFotografij() {

    var ime:String by remember { mutableStateOf("") }
    var spol:Char by remember { mutableStateOf('m') }
    var starost:String by remember { mutableStateOf("") }

    val osebe:MutableList<Oseba> = remember {
        mutableListOf<Oseba>()
    }

    Column {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text("Ime:")

            TextField(
                value = ime,
                onValueChange = {
                    ime = it
                },
                modifier = Modifier.width(50.dp)
            )

            Spacer(modifier = Modifier.width(2.dp))

            Text("Starost:")

            TextField(
                value = starost,
                onValueChange = {
                    starost = it
                },
                modifier = Modifier.width(50.dp)
            )

            Spacer(modifier = Modifier.width(2.dp))

            Text("Spol:")

            RadioButton(
                selected = spol == 'm',
                onClick = {
                    spol = 'm'
                }
            )

            Text("moški")

            RadioButton(
                selected = spol == 'z',
                onClick = {
                    spol = 'z'
                }
            )

            Text("ženski")
        }


        Button(
            onClick = {

                osebe.add(
                    Oseba(
                        ime,
                        starost.toInt(),
                        spol
                    )
                )

                spol = 'm'
                ime = ""
                starost = ""
            }
        ) {

            Text("Dodaj")
        }
    }


    LazyColumn {

        items(osebe) { oseba ->

            Vrstica(oseba)
        }
    }
}

@Composable
fun Vrstica(oseba: Oseba) {

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter = painterResource(
                id = if (oseba.spol == 'm') {
                    R.drawable.moski
                } else {
                    R.drawable.zenska
                }
            ),
            contentDescription = oseba.ime,
            modifier = Modifier
                .size(120.dp)
                .padding(end = 12.dp)
        )

        Column {

            Text("Ime: ${oseba.ime}")

            Text("Starost: ${oseba.starost}")
        }
    }
}