package com.example.drave

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.drave.model.SavedCountry
import com.example.drave.network.RetrofitInstance
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Drzave()
        }
    }
}

fun shraniDrzave(context: Context, drzave: List<SavedCountry>) {
    val sharedPreferences =
        context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    val gson = Gson()
    val jsonBesedilo = gson.toJson(drzave)

    sharedPreferences
        .edit()
        .putString("DRZAVE", jsonBesedilo)
        .apply()
}

fun naloziDrzave(context: Context): List<SavedCountry> {
    val sharedPreferences =
        context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    val gson = Gson()

    val jsonBesedilo =
        sharedPreferences.getString("DRZAVE", null)

    return if (jsonBesedilo != null) {
        val type =
            object : TypeToken<List<SavedCountry>>() {}.type

        gson.fromJson(jsonBesedilo, type)
    } else {
        emptyList()
    }
}

@Composable
fun Drzave() {
    val context = LocalContext.current

    val shranjeneDrzave =
        remember { mutableStateListOf<SavedCountry>() }

    var iskanaDrzava by remember { mutableStateOf("") }
    var isci by remember { mutableStateOf(false) }

    var ime by remember { mutableStateOf("") }
    var uradnoIme by remember { mutableStateOf("") }
    var glavnoMesto by remember { mutableStateOf("") }
    var regija by remember { mutableStateOf("") }
    var prebivalstvo by remember { mutableStateOf("") }
    var jeziki by remember { mutableStateOf("") }
    var napaka by remember { mutableStateOf("") }

    var prioriteta by remember { mutableStateOf("srednja") }

    LaunchedEffect(Unit) {
        shranjeneDrzave.addAll(naloziDrzave(context))
    }

    LaunchedEffect(isci) {
        if (isci == true) {
            napaka = ""

            try {

                val odgovor =
                    RetrofitInstance.api.getCountry(
                        iskanaDrzava,
                        "Bearer rc_live_3a368130e2bd4d47b568700d40c4a8ee"
                    )

                val rezultat =
                    odgovor.data.objects

                if (rezultat.size > 0) {

                    val drzava =
                        rezultat[0]

                    ime =
                        drzava.names.common

                    uradnoIme =
                        drzava.names.official

                    if (drzava.capitals != null) {

                        glavnoMesto =
                            drzava.capitals[0].name

                    } else {

                        glavnoMesto =
                            "Ni podatka"

                    }

                    regija =
                        drzava.region

                    prebivalstvo =
                        drzava.population.toString()

                    if (drzava.languages != null) {

                        jeziki =
                            drzava.languages[0].name

                    } else {

                        jeziki =
                            "Ni podatka"

                    }

                } else {

                    napaka =
                        "Ni rezultatov"

                }
            } catch (e: Exception) {

                napaka =
                    e.message.toString()

            }

            isci = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .statusBarsPadding()
    ) {
        Text(
            text = "Iskanje držav",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = iskanaDrzava,
            onValueChange = { iskanaDrzava = it },
            label = { Text("Vnesi državo (v angleščini)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))


        Button(
            onClick = {
                isci = true
            }
        ) {
            Text("Poišči")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (ime != "") {
            Text("Ime: $ime")
            Text("Uradno ime: $uradnoIme")
            Text("Glavno mesto: $glavnoMesto")
            Text("Regija: $regija")
            Text("Prebivalstvo: $prebivalstvo")
            Text("Jeziki: $jeziki")

            Text("Prioriteta:")

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                RadioButton(
                    selected = prioriteta == "nizka",
                    onClick = { prioriteta = "nizka" }
                )

                Text("nizka")

                Spacer(modifier = Modifier.width(12.dp))


                RadioButton(
                    selected = prioriteta == "srednja",
                    onClick = { prioriteta = "srednja" }
                )

                Text("srednja")

                Spacer(modifier = Modifier.width(12.dp))


                RadioButton(
                    selected = prioriteta == "visoka",
                    onClick = { prioriteta = "visoka" }
                )

                Text("visoka")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        val novaDrzava =
                            SavedCountry(
                                name = ime,
                                capital = glavnoMesto,
                                region = regija,
                                population = prebivalstvo.toInt(),
                                languages = jeziki,
                                visited = false,
                                priority = prioriteta
                            )

                        shranjeneDrzave.add(novaDrzava)
                        shraniDrzave(context, shranjeneDrzave)
                    }
                ) {
                    Text("Shrani državo")
                }

                Button(
                    onClick = {
                        iskanaDrzava = ""
                        ime = ""
                        uradnoIme = ""
                        glavnoMesto = ""
                        regija = ""
                        prebivalstvo = ""
                        jeziki = ""
                    }
                ) {
                    Text("Zapri")
                }
            }
        }

        if (napaka != "") {
            Text(napaka)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Shranjene države",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            itemsIndexed(shranjeneDrzave) { index, drzava ->

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .border(
                            width = 1.dp,
                            color = Color(0xFFD9CCF5),
                            shape = RoundedCornerShape(20.dp)
                        )
                        .background(
                            color = Color(0xFFF7F2FF),
                            shape = RoundedCornerShape(20.dp)
                        )
                        .padding(16.dp)
                ) {
                    Text(
                        text = drzava.name,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text("Glavno mesto: ${drzava.capital}", fontSize = 15.sp)
                    Text("Regija: ${drzava.region}", fontSize = 15.sp)
                    Text("Prebivalstvo: ${drzava.population}", fontSize = 15.sp)
                    Text("Jeziki: ${drzava.languages}", fontSize = 15.sp)
                    Text("Prioriteta: ${drzava.priority}", fontSize = 15.sp)

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Obiskana", fontSize = 15.sp)

                            Spacer(modifier = Modifier.width(8.dp))

                            Switch(
                                checked = drzava.visited,
                                onCheckedChange = { novoStanje ->
                                    val popravljenaDrzava =
                                        SavedCountry(
                                            name = drzava.name,
                                            capital = drzava.capital,
                                            region = drzava.region,
                                            population = drzava.population,
                                            languages = drzava.languages,
                                            visited = novoStanje,
                                            priority = drzava.priority
                                        )

                                    shranjeneDrzave[index] = popravljenaDrzava
                                    shraniDrzave(context, shranjeneDrzave)
                                }
                            )
                        }

                        Button(
                            onClick = {
                                shranjeneDrzave.removeAt(index)
                                shraniDrzave(context, shranjeneDrzave)
                            }
                        ) {
                            Text("Izbriši")
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}