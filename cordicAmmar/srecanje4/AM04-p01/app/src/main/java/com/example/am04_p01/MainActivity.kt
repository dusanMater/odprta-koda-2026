package com.example.am04_p01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import model.Oseba

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Column (
                modifier = Modifier
                    .statusBarsPadding()
            ) {
                PrikazFotografij()
            }
        }
    }
}

@Composable
fun PrikazFotografij() {
    var ime by remember { mutableStateOf("") }
    var starost by remember { mutableStateOf("") }
    var spol by remember { mutableStateOf("M") }
    val osebe = remember { mutableStateListOf<Oseba>() }

    Column () {
        Column () {
            Row() {
                Text("Ime: ")
                TextField(
                    value = ime,
                    onValueChange = {
                        ime = it
                    },
                    modifier = Modifier.width(80.dp)
                )

                Text("Starost: ")
                TextField(
                    value = starost,
                    onValueChange = {
                        starost = it
                    },
                    modifier = Modifier.width(40.dp)
                )

                Text("Spol: ")
                RadioButton(
                    selected = spol == "M",
                    onClick = {
                        spol = "M"
                    }
                )
                Text("Moški")

                RadioButton(
                    selected = spol == "Z",
                    onClick = {
                        spol = "Z"
                    }
                )
                Text("Ženski")
            }

            Button(
                onClick = {
                    val starostInt = starost.toIntOrNull()
                    if (ime.isNotBlank() && starostInt != null) {
                        osebe.add(
                            Oseba(
                                ime,
                                starostInt,
                                if (spol == "M") R.drawable.male else R.drawable.female,
                                spol
                            )
                        )
                        ime = ""
                        starost = ""
                    }
                }
            ) {
                Text("Dodaj osebo")
            }

        }


        LazyColumn () {
            items(osebe) { oseba ->
                Vrstica(oseba)
            }
        }
    }


}


@Composable
fun Vrstica(oseba: Oseba) {
    Row () {
        Image(
            painter = painterResource(
                if (oseba.spol == "M")
                    R.drawable.male
                else
                    R.drawable.female
            ),
            contentDescription = oseba.ime,
            modifier = Modifier
                .size(169.dp)
                .padding(bottom = 12.dp)
        )

        Column () {
            Text("Ime: " + oseba.ime)
            Text("Starost: " + oseba.starost.toString())
        }
    }
}
