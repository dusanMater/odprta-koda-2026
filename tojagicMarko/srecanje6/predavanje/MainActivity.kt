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
import androidx.compose.runtime.snapshots.SnapshotStateList
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
            var artikel by remember { mutableStateOf("") }
            val artikli = remember { mutableStateListOf<Artikel>() }
            val context = LocalContext.current

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = 8.dp)
            ) {
                Spacer(modifier = Modifier.height(40.dp))
                LaunchedEffect(key1 = Unit) {
                    val shranjeneOsbe = loadData(context)
                    artikli.clear()
                    artikli.addAll(shranjeneOsbe)
                }

                VnosArtilov(
                    artikel = artikel,
                    onValueChange = { artikel = it },
                    modifier = Modifier.fillMaxWidth()
                )

                Button(
                    onClick = {
                        if (artikel.isNotBlank()) {
                            artikli.add(Artikel(ime = artikel, jeVKosarici = false))
                            artikel = ""
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
                    items(artikli) { artikel ->
                        IzpisArtikla(artikel, Modifier.fillMaxWidth())
                    }
                }

                Button(
                    onClick = {
                        saveData(context, artikli)
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
fun VnosArtilov(
    artikel: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        onValueChange = onValueChange,
        value = artikel,
        modifier = modifier
    )
}

@Composable
fun IzpisArtikla(artikel: Artikel, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
    ) {
        Text(
            text = artikel.ime,
            modifier = Modifier.weight(1f)
        )
        Checkbox(
            checked = artikel.jeVKosarici,
            onCheckedChange = {  }
        )
    }
}

fun saveData(context: Context, artikli: List<Artikel>) {
    val s = context.getSharedPreferences("Osebe", Context.MODE_PRIVATE)
    val editor = s.edit()
    val gson = Gson()
    val json = gson.toJson(artikli)
    editor.putString("OSEBE", json)
    editor.apply()
}

fun loadData(context: Context): List<Artikel> {
    val s = context.getSharedPreferences("Osebe", Context.MODE_PRIVATE)
    val gson = Gson()
    val json = s.getString("OSEBE", null)
    return if (json != null) {
        val type = object : TypeToken<List<Artikel>>() {}.type
        gson.fromJson(json, type)
    } else {
        emptyList()
    }
}