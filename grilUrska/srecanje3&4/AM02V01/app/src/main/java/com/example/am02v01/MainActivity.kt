package com.example.am02v01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import model.Oseba
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateListOf

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Column(modifier = Modifier.statusBarsPadding()) {
                PrikazFotografije()
            }
        }
    }
}

@Composable
fun PrikazFotografije(){


    var ime:String by remember { mutableStateOf("") }
    var starost:String by remember { mutableStateOf("") }
    var spol:Char by remember { mutableStateOf(' ') }

    val osebe = remember { mutableStateListOf<Oseba>() }

    Column(){
        Row(modifier = Modifier.padding(16.dp)){
            Text("Ime: ")
            TextField(
                value = ime,
                onValueChange = {ime = it},
                modifier = Modifier.fillMaxWidth())
        }
        Row(modifier = Modifier.padding(16.dp)){
            Text("Starost: ")
            TextField(
                value = starost,
                onValueChange = {starost = it},
                modifier = Modifier.fillMaxWidth())
        }
        Row(modifier = Modifier.padding(16.dp)){
            Text("Spol: ")
            RadioButton(
                selected = spol == 'z',
                onClick = {spol = 'z'}
            )
            Text("Ženski")
            RadioButton(
                selected = spol == 'm',
                onClick = {spol = 'm'
                }
            )
            Text("Moški")
        }
        Button(
            modifier = Modifier.padding(16.dp),
            onClick = {
                osebe.add(Oseba(ime, starost.toInt(), spol))
                ime = ""
                starost = ""
                spol = ' '            }
        ){Text("Dodaj")}
        LazyColumn()  {
            items(osebe) {oseba ->
                Vrstica(oseba)
            }
    }
}
}
@Composable
fun Vrstica(oseba: Oseba) {
    Row(){
        Image(
            painter = painterResource(
                if (oseba.spol == 'm') {
                    R.drawable.m
                } else {
                    R.drawable.f
                }
            ),
            contentDescription = oseba.ime,
            modifier = Modifier.size(160.dp).padding(bottom = 12.dp)
        )
        Column(verticalArrangement = Arrangement.Center, modifier = Modifier.size(160.dp).padding(bottom = 12.dp)){
            Text("Ime: " + oseba.ime)
            Text("Starost: " + oseba.starost)
        }
    }


}
