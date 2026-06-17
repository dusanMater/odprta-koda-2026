package com.example.am_seznamartiklovdemo

import android.os.Bundle
import android.content.Context
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import com.example.am_seznamartiklovdemo.model.Artikel
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var vnos by remember { mutableStateOf("") }
            val seznamIzdelkov = remember { mutableStateListOf<Artikel>() }
            val kontekst = LocalContext.current

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = 8.dp)
            ) {
                Spacer(modifier = Modifier.height(40.dp))

                LaunchedEffect(key1 = Unit) {
                    val shranjeni = LoadData(kontekst)
                    seznamIzdelkov.clear()
                    seznamIzdelkov.addAll(shranjeni)
                }

                VnosIzdelka(
                    vnos = vnos,
                    obSpremembi = { vnos = it },
                    modifikator = Modifier.fillMaxWidth()
                )

                Button(
                    onClick = {
                        if (vnos.isNotBlank()) {
                            seznamIzdelkov.add(Artikel(ime = vnos, jeVKosarici = false))
                            vnos = ""
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Dodaj v seznam")
                }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .border(width = 1.dp, color = Color.Red)
                ) {
                    items(seznamIzdelkov) { izdelek ->
                        PrikazIzdelka(izdelek, Modifier.fillMaxWidth())
                    }
                }

                Button(
                    onClick = {
                        SaveData(kontekst, seznamIzdelkov)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Shrani")
                }
            }
        }
    }
}

@Composable
fun VnosIzdelka(
    vnos: String,
    obSpremembi: (String) -> Unit,
    modifikator: Modifier = Modifier
) {
    TextField(
        value = vnos,
        onValueChange = obSpremembi,
        modifier = modifikator
    )
}

@Composable
fun PrikazIzdelka(izdelek: Artikel, modifikator: Modifier = Modifier) {
    Row(modifier = modifikator) {
        Text(
            text = izdelek.ime,
            modifier = Modifier.weight(1f)
        )
        Checkbox(
            checked = izdelek.jeVKosarici,
            onCheckedChange = { }
        )
    }
}

fun SaveData(kontekst: Context, seznam: List<Artikel>) {
    val skupnePref = kontekst.getSharedPreferences("Osebe", Context.MODE_PRIVATE)
    val urednik = skupnePref.edit()
    val gson = Gson()
    val json = gson.toJson(seznam)
    urednik.putString("OSEBE", json)
    urednik.apply()
}

fun LoadData(kontekst: Context): List<Artikel> {
    val skupnePref = kontekst.getSharedPreferences("Osebe", Context.MODE_PRIVATE)
    val gson = Gson()
    val json = skupnePref.getString("OSEBE", null)
    return if (json != null) {
        val tip = object : TypeToken<List<Artikel>>() {}.type
        gson.fromJson(json, tip)
    } else {
        emptyList()
    }
}