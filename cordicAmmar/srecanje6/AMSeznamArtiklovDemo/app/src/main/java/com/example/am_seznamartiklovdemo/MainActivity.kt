package com.example.am_seznamartiklovdemo

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.am_seznamartiklovdemo.model.Artikel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var artikel: String by remember { mutableStateOf("") }
            val artikli = remember { mutableStateListOf<Artikel>() }

            val context: Context = LocalContext.current

            Column (
                modifier = Modifier
                    .statusBarsPadding()
                    .fillMaxSize()
                    .padding(8.dp)
            ) {

                LaunchedEffect(Unit) {
                    var shranjeniArtikli = loadData(context)
                    artikli.clear()
                    artikli.addAll(shranjeniArtikli)
                }

                // Input field
                VnosArtiklov(artikel, { artikel = it }, Modifier.fillMaxWidth())

                // Add button
                Button(
                    onClick = {
                        if (artikel.isNotBlank()) {
                            var present: Boolean = false
                            for (a in artikli) {
                                if (a.ime == artikel) {
                                    present = true
                                    break
                                }
                            }
                            if (!present) {
                                artikli.add(Artikel(artikel, false))
                            }
                        }
                        artikel = ""
                    },
                    modifier = Modifier.fillMaxWidth().padding(4.dp)
                ) {
                    Text("Dodaj v seznam")
                }

                // List of items
                LazyColumn (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                        .weight(1f)
                        .border(width = 1.dp, color = Color.Red)
                ) {
                    items(artikli) { artikel ->
                        IzpisArtikla(artikel, Modifier.fillMaxWidth())
                    }
                }

                // Save button for saving of list to shared preferences
                Button(
                    onClick = {
                        saveData(context, artikli)
                    },
                    modifier = Modifier.fillMaxWidth().padding(4.dp)
                ) {
                    Text("Shrani")
                }

            }

        }
    }
}

@Composable
fun VnosArtiklov(artikel: String, inputOfArtikel: (String) -> Unit, modifier: Modifier = Modifier) {
    TextField(
        value = artikel,
        onValueChange = inputOfArtikel,
        label = { Text("Kaj boš kupil?") },
        modifier = Modifier.fillMaxWidth().padding(4.dp)
    )
}

@Composable
fun IzpisArtikla(artikel: Artikel, modifier: Modifier = Modifier) {

    Row (
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(artikel.ime, modifier = Modifier.padding(8.dp))
        Checkbox(
            checked = artikel.jeVKosarici,
            onCheckedChange = { /* Handle checkbox state change */ },
            modifier = Modifier.padding(8.dp)
        )
    }
}

fun saveData(context: Context, artikli:List<Artikel>) {
    val s: SharedPreferences = context.getSharedPreferences("Artikli", Context.MODE_PRIVATE)
    val editor = s.edit()

    val gson = Gson()
    val json: String = gson.toJson(artikli)
    editor.putString("artikli", json)
    editor.apply()
}

fun loadData(context: Context): List<Artikel> {
    val s: SharedPreferences = context.getSharedPreferences("Artikli", Context.MODE_PRIVATE)
    val gson = Gson()
    val json: String? = s.getString("artikli", null)

    return if (json != null) {
        val type = object: TypeToken<List<Artikel>>() {}.type
        gson.fromJson(json, type)
    } else {
        emptyList()
    }
    //val artikli: Array<Artikel> = gson.fromJson(json, Array<Artikel>::class.java)
    // return artikli.toList()

}