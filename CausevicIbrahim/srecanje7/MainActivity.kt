package com.example.domacanaloga7_vreme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {

    private val apiKljuc = "800046066f87a8e986c1e56fefdc4899"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VremenskaAplikacija(apiKljuc)
        }
    }
}
data class VremeInfo(
    val mesto: String,
    val cas: String,
    val opis: String,
    val temperatura: String,
    val emoji: String,
    val veter: String,
    val vlaga: String,
    val soncniVzhod: String,
    val soncniZahod: String
)
@Composable
fun VremenskaAplikacija(kljuc: String) {
    val mesta = listOf("Ljubljana", "Maribor", "Koper")

    var izbranoMesto by remember { mutableStateOf(mesta[0]) }
    var razsirjeno by remember { mutableStateOf(false) }
    var vreme by remember { mutableStateOf<VremeInfo?>(null) }
    var napaka by remember { mutableStateOf("") }

    LaunchedEffect(izbranoMesto) {
        try {
            vreme = pridobiVreme(izbranoMesto, kljuc)
            napaka = ""
        } catch (e: Exception) {
            napaka = "Nekaj je šlo narobe pri vremenu."
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0878A8))
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Vreme",
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        Box {
            Button(onClick = { razsirjeno = true }) {
                Text(izbranoMesto)
            }

            DropdownMenu(
                expanded = razsirjeno,
                onDismissRequest = { razsirjeno = false }
            ) {
                mesta.forEach { m ->
                    DropdownMenuItem(
                        text = { Text(m) },
                        onClick = {
                            izbranoMesto = m
                            razsirjeno = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        if (napaka.isNotEmpty()) {
            Text(napaka, color = Color.White)
        } else if (vreme == null) {
            CircularProgressIndicator(color = Color.White)
        } else {
            Vreme(vreme!!)
        }
    }
}

@Composable
fun Vreme(v: VremeInfo) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("${v.mesto}, ${v.cas}", color = Color.White.copy(alpha = 0.8f))
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = v.opis.replaceFirstChar { it.uppercase() },
            color = Color.White,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = v.temperatura,
                color = Color.White,
                fontSize = 70.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.width(25.dp))

            Box(
                modifier = Modifier
                    .size(110.dp)
                    .background(Color.White.copy(alpha = 0.25f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(v.emoji, fontSize = 55.sp)
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        InfoVrstica("Hitrost vetra", v.veter)
        InfoVrstica("Vlažnost", v.vlaga)
        InfoVrstica("Sončni vzhod", v.soncniVzhod)
        InfoVrstica("Sončni zahod", v.soncniZahod)

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Napoved za naprej",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(10.dp))

        NapovedVrstica("Čez 1 uro", v.temperatura, v.emoji)
        NapovedVrstica("Čez 2 uri", v.temperatura, v.emoji)
        NapovedVrstica("Čez 3 ure", v.temperatura, v.emoji)
    }
}

@Composable
fun InfoVrstica(oznaka: String, vrednost: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(oznaka, color = Color.White.copy(alpha = 0.75f))
        Text(vrednost, color = Color.White, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun NapovedVrstica(cas: String, temp: String, emoji: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White.copy(alpha = 0.10f))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(cas, color = Color.White)
        Text(temp, color = Color.White, fontSize = 22.sp)
        Text(emoji, fontSize = 28.sp)
    }

    Spacer(modifier = Modifier.height(6.dp))
}

suspend fun pridobiVreme(mesto: String, kljuc: String): VremeInfo {
    return withContext(Dispatchers.IO) {
        val urlTekst = "https://api.openweathermap.org/data/2.5/weather?q=$mesto,SI&appid=$kljuc&units=metric&lang=sl"

        val url = URL(urlTekst)
        val povezava = url.openConnection() as HttpURLConnection
        povezava.requestMethod = "GET"

        val odziv = povezava.inputStream.bufferedReader().readText()
        val json = JSONObject(odziv)

        val vremeObject = json.getJSONArray("weather").getJSONObject(0)
        val glavno = json.getJSONObject("main")
        val veterObject = json.getJSONObject("wind")
        val sonceObject = json.getJSONObject("sys")

        val opis = vremeObject.getString("description")
        val temp = glavno.getDouble("temp").toInt()
        val vlaga = glavno.getInt("humidity")
        val hitrostVetra = veterObject.getDouble("speed")
        val smerVetra = veterObject.optInt("deg", 0)

        val vzhod = sonceObject.getLong("sunrise")
        val zahod = sonceObject.getLong("sunset")
        val casMeritve = json.getLong("dt")

        VremeInfo(
            mesto = mesto,
            cas = formatirajCas(casMeritve),
            opis = opis,
            temperatura = "$temp °C",
            emoji = emojiVremena(opis),
            veter = "$hitrostVetra m/s, ${smerVetraString(smerVetra)}",
            vlaga = "$vlaga %",
            soncniVzhod = formatirajCas(vzhod),
            soncniZahod = formatirajCas(zahod)
        )
    }
}

fun formatirajCas(sekunde: Long): String {
    val datum = Date(sekunde * 1000)
    val format = SimpleDateFormat("HH:mm", Locale.getDefault())
    return format.format(datum)
}

fun emojiVremena(opis: String): String {
    val o = opis.lowercase()

    return when {
        o.contains("dež") || o.contains("rain") -> "🌧️"
        o.contains("sneg") || o.contains("snow") -> "❄️"
        o.contains("megla") || o.contains("fog") || o.contains("mist") -> "🌫️"
        o.contains("obla") || o.contains("cloud") -> "☁️"
        o.contains("jasno") || o.contains("clear") -> "☀️"
        else -> "🌤️"
    }
}

fun smerVetraString(stopinje: Int): String {
    return when (stopinje) {
        in 0..22 -> "S"
        in 23..67 -> "SV"
        in 68..112 -> "V"
        in 113..157 -> "JV"
        in 158..202 -> "J"
        in 203..247 -> "JZ"
        in 248..292 -> "Z"
        in 293..337 -> "SZ"
        else -> "S"
    }
}