package com.example.domacanaloga_7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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

    private val apiKljuc = "b68a8ce6f91983fdcc4d3dafddcae5a7"

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

data class PrognozaInfo(
    val cas: String,
    val temperatura: String,
    val emoji: String
)

@Composable
fun VremenskaAplikacija(kljuc: String) {
    val mesta = listOf("Ljubljana", "Maribor", "Koper")

    var izbranoMesto by remember { mutableStateOf(mesta[0]) }
    var razsirjeno by remember { mutableStateOf(false) }
    var vreme by remember { mutableStateOf<VremeInfo?>(null) }
    var prognoze by remember { mutableStateOf<List<PrognozaInfo>>(emptyList()) }
    var napaka by remember { mutableStateOf("") }

    LaunchedEffect(izbranoMesto) {
        try {
            val (trenutno, seznamPrognoz) = pridobiVremeInPrognozo(izbranoMesto, kljuc)
            vreme = trenutno
            prognoze = seznamPrognoz
            napaka = ""
        } catch (_: Exception) {
            napaka = "Nekaj je šlo narobe pri vremenu."
        }
    }

    val gradient = Brush.linearGradient(
        colors = listOf(Color(0xFF1A2A3A), Color(0xFF0F1A24)),
        start = Offset(0f, 0f),
        end = Offset(0f, Float.POSITIVE_INFINITY)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "🌤️ Vreme",
            color = Color.White,
            fontSize = 34.sp,
            fontWeight = FontWeight.Light,
            letterSpacing = 2.sp,
            modifier = Modifier.shadow(4.dp, RoundedCornerShape(8.dp))
        )

        Spacer(modifier = Modifier.height(24.dp))

        Box {
            OutlinedButton(
                onClick = { razsirjeno = true },
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.White,
                    containerColor = Color.Transparent
                )
            ) {
                Text(izbranoMesto, fontSize = 18.sp)
            }

            DropdownMenu(
                expanded = razsirjeno,
                onDismissRequest = { razsirjeno = false },
                modifier = Modifier.background(Color(0xFF2C3E50))
            ) {
                mesta.forEach { m ->
                    DropdownMenuItem(
                        text = { Text(m, color = Color.White) },
                        onClick = {
                            izbranoMesto = m
                            razsirjeno = false
                        },
                        modifier = Modifier.background(Color.Transparent)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        if (napaka.isNotEmpty()) {
            Card(
                colors = CardDefaults.cardColors(containerColor = Color(0x99FF5555)),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.padding(8.dp)
            ) {
                Text(napaka, color = Color.White, modifier = Modifier.padding(16.dp))
            }
        } else if (vreme == null) {
            CircularProgressIndicator(color = Color(0xFF64B5F6))
        } else {
            Vreme(vreme!!, prognoze)
        }
    }
}

@Composable
fun Vreme(vremePodatki: VremeInfo, prognoze: List<PrognozaInfo>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .shadow(12.dp, RoundedCornerShape(32.dp)),
            shape = RoundedCornerShape(32.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xAA1E2A36)
            )
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "${vremePodatki.mesto}, ${vremePodatki.cas}",
                    color = Color(0xFFB0D4FF),
                    fontSize = 16.sp,
                    letterSpacing = 0.5.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = vremePodatki.opis.replaceFirstChar { it.uppercase() },
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = vremePodatki.temperatura,
                        color = Color.White,
                        fontSize = 68.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp
                    )

                    Spacer(modifier = Modifier.width(20.dp))

                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .clip(RoundedCornerShape(30.dp))
                            .background(Color.White.copy(alpha = 0.15f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(vremePodatki.emoji, fontSize = 54.sp)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xCC1E2A36))
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                InfoVrstica("💨 Hitrost vetra", vremePodatki.veter)
                InfoVrstica("💧 Vlažnost", vremePodatki.vlaga)
                Divider(color = Color.White.copy(alpha = 0.2f), modifier = Modifier.padding(vertical = 8.dp))
                InfoVrstica("🌅 Sončni vzhod", vremePodatki.soncniVzhod)
                InfoVrstica("🌇 Sončni zahod", vremePodatki.soncniZahod)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "📆 Napoved",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        if (prognoze.size >= 3) {
            NapovedVrstica("Čez 1 uro", prognoze[0].temperatura, prognoze[0].emoji)
            NapovedVrstica("Čez 2 uri", prognoze[1].temperatura, prognoze[1].emoji)
            NapovedVrstica("Čez 3 ure", prognoze[2].temperatura, prognoze[2].emoji)
        } else {
            NapovedVrstica("Čez 1 uro", vremePodatki.temperatura, vremePodatki.emoji)
            NapovedVrstica("Čez 2 uri", vremePodatki.temperatura, vremePodatki.emoji)
            NapovedVrstica("Čez 3 ure", vremePodatki.temperatura, vremePodatki.emoji)
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun InfoVrstica(oznaka: String, vrednost: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(oznaka, color = Color(0xFFB8D0E8), fontSize = 15.sp)
        Text(vrednost, color = Color.White, fontWeight = FontWeight.Medium, fontSize = 15.sp)
    }
}

@Composable
fun NapovedVrstica(cas: String, temp: String, emoji: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0x882C3E50))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(cas, color = Color.White, fontSize = 16.sp)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(temp, color = Color(0xFFE0F2FE), fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(12.dp))
                Text(emoji, fontSize = 26.sp)
            }
        }
    }
}

suspend fun pridobiVremeInPrognozo(mesto: String, kljuc: String): Pair<VremeInfo, List<PrognozaInfo>> {
    return withContext(Dispatchers.IO) {
        val urlTrenutno = "https://api.openweathermap.org/data/2.5/weather?q=$mesto,SI&appid=$kljuc&units=metric&lang=sl"
        val trenutnoJson = URL(urlTrenutno).openConnection().let {
            it as HttpURLConnection
            it.inputStream.bufferedReader().readText()
        }.let { JSONObject(it) }

        val objektVreme = trenutnoJson.getJSONArray("weather").getJSONObject(0)
        val glavno = trenutnoJson.getJSONObject("main")
        val objektVeter = trenutnoJson.getJSONObject("wind")
        val objektSonce = trenutnoJson.getJSONObject("sys")

        val opis = objektVreme.getString("description")
        val temp = glavno.getDouble("temp").toInt()
        val vlaga = glavno.getInt("humidity")
        val hitrostVetra = objektVeter.getDouble("speed")
        val smerVetra = objektVeter.optInt("deg", 0)
        val vzhod = objektSonce.getLong("sunrise")
        val zahod = objektSonce.getLong("sunset")
        val casMeritve = trenutnoJson.getLong("dt")

        val trenutno = VremeInfo(
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

        val urlPrognoza = "https://api.openweathermap.org/data/2.5/forecast?q=$mesto,SI&appid=$kljuc&units=metric&lang=sl"
        val prognozaJson = URL(urlPrognoza).openConnection().let {
            it as HttpURLConnection
            it.inputStream.bufferedReader().readText()
        }.let { JSONObject(it) }

        val seznam = prognozaJson.getJSONArray("list")
        val sedaj = System.currentTimeMillis() / 1000
        val prognoze = mutableListOf<PrognozaInfo>()
        var presteto = 0

        for (i in 0 until seznam.length()) {
            if (presteto >= 3) break
            val postavka = seznam.getJSONObject(i)
            val casUnix = postavka.getLong("dt")
            if (casUnix > sedaj) {
                val tempNapoved = postavka.getJSONObject("main").getDouble("temp").toInt()
                val vremeNapoved = postavka.getJSONArray("weather").getJSONObject(0)
                val opisNapoved = vremeNapoved.getString("description")
                val ura = formatirajCas(casUnix)
                prognoze.add(
                    PrognozaInfo(
                        cas = ura,
                        temperatura = "$tempNapoved °C",
                        emoji = emojiVremena(opisNapoved)
                    )
                )
                presteto++
            }
        }

        Pair(trenutno, prognoze)
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