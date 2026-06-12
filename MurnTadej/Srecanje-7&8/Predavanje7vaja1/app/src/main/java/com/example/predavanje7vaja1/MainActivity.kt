package com.example.predavanje7vaja1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Seznam mest — dodaj/odvzemi mesto tukaj
            val mesta = listOf("Novo mesto", "Ljubljana", "Koper")

            var mesto by remember { mutableStateOf(mesta.first()) }
            var temperatura by remember { mutableStateOf("Nalagam...") }
            var opis by remember { mutableStateOf("") }
            var vlaznost by remember { mutableStateOf("") }
            var cas by remember { mutableStateOf("") }
            var veter by remember { mutableStateOf("") }
            var vzhod by remember { mutableStateOf("") }
            var zahod by remember { mutableStateOf("") }
            var ikona by remember { mutableStateOf("") }
            var napoved by remember { mutableStateOf<List<ForecastData>>(emptyList()) }

            LaunchedEffect(mesto) {
                try {
                    val rezultat = RetrofitInstance.api.getWeather(
                        city = mesto,
                        apiKey = "34fdcde892dc6e19e9467e84cc58e428"
                    )
                    temperatura = "${rezultat.main.temp} °C"
                    opis = rezultat.weather.firstOrNull()?.description ?: "Ni podatka"
                    vlaznost = "Vlažnost: ${rezultat.main.humidity} %"
                    cas = "Čas meritve: ${formatCas(rezultat.dt)}"
                    veter = "Veter: ${rezultat.wind.speed} m/s, ${smerVetra(rezultat.wind.deg)}, ${opisVetra(rezultat.wind.speed)}"
                    vzhod = "Sončni vzhod: ${formatCas(rezultat.sys.sunrise)}"
                    zahod = "Sončni zahod: ${formatCas(rezultat.sys.sunset)}"
                    val napovedRezultat = RetrofitInstance.api.getForecast(
                        city = mesto,
                        apiKey = "34fdcde892dc6e19e9467e84cc58e428"
                    )
                    napoved = napovedRezultat.list
                    ikona = ikonaVremena(rezultat.weather.firstOrNull()?.main ?: "")
                } catch (e: Exception) {
                    temperatura = "Napaka: ${e.message}"
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color(0xFF4A90D9), Color(0xFF0D47A1))
                        )
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Izbira mesta — drsna vrsta gumbov (vedno prostor za novo)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        mesta.forEach { m ->
                            FilterChip(
                                selected = (m == mesto),
                                onClick = { mesto = m },
                                label = { Text(m) }
                            )
                        }
                    }

                    Spacer(Modifier.height(40.dp))

                    Text(mesto, fontSize = 30.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    Text(opis, fontSize = 18.sp, color = Color.White)

                    Spacer(Modifier.height(16.dp))

                    Text(ikona, fontSize = 72.sp)

                    Text(temperatura, fontSize = 64.sp, fontWeight = FontWeight.Bold, color = Color.White)

                    Spacer(Modifier.height(40.dp))

                    // Kartica s podrobnostmi
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White.copy(alpha = 0.15f)
                        )
                    ) {
                        Column(    modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .statusBarsPadding()
                            .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(cas, color = Color.White, fontSize = 16.sp)
                            Text(vlaznost, color = Color.White, fontSize = 16.sp)
                            Text(veter, color = Color.White, fontSize = 16.sp)
                            Text(vzhod, color = Color.White, fontSize = 16.sp)
                            Text(zahod, color = Color.White, fontSize = 16.sp)
                            Spacer(Modifier.height(24.dp))
                            Text("Napoved", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                            Spacer(Modifier.height(8.dp))
                            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                items(napoved) { vnos ->
                                    Card(
                                        colors = CardDefaults.cardColors(
                                            containerColor = Color.White.copy(alpha = 0.15f)
                                        )
                                    ) {
                                        Column(
                                            modifier = Modifier.padding(12.dp),
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Text(formatNapoved(vnos.dt_txt), color = Color.White, fontSize = 13.sp)
                                            Text(ikonaVremena(vnos.weather.firstOrNull()?.main ?: ""), fontSize = 32.sp)
                                            Text("${vnos.main.temp}°", color = Color.White, fontSize = 16.sp)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

object RetrofitInstance {
    val api: WeatherApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }
}

fun smerVetra(stopinje: Int): String {
    val smeri = listOf("S", "SV", "V", "JV", "J", "JZ", "Z", "SZ")
    return smeri[((stopinje + 22) / 45) % 8]
}

fun opisVetra(hitrost: Double): String = when {
    hitrost < 1 -> "brezvetrje"
    hitrost < 4 -> "šibek"
    hitrost < 8 -> "zmeren"
    hitrost < 14 -> "močan"
    else -> "zelo močan"
}

fun formatCas(timestamp: Long): String {
    val datum = java.util.Date(timestamp * 1000)
    val format = java.text.SimpleDateFormat("HH:mm", java.util.Locale.getDefault())
    return format.format(datum)
}

fun ikonaVremena(glavno: String): String = when (glavno) {
    "Clear" -> "☀️"
    "Clouds" -> "☁️"
    "Rain", "Drizzle" -> "🌧️"
    "Snow" -> "❄️"
    "Mist", "Fog", "Haze" -> "🌫️"
    else -> "🌡️"
}

fun formatNapoved(dtTxt: String): String {
    return dtTxt.substring(5, 16)   // "2026-06-04 15:00:00" -> "06-04 15:00"
}