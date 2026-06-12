package com.example.predavanje7ucenje

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var temperatura by remember { mutableStateOf("Nalagam...") }
            var opis by remember { mutableStateOf("") }
            var vlaznost by remember { mutableStateOf("") }

            LaunchedEffect(Unit) {
                try {
                    val rezultat = RetrofitInstance.api.getWeather(
                        city = "Maribor",
                        apiKey = "43ad46096311291943947de891c93e9a"
                    )
                    temperatura = "${rezultat.main.temp} °C"
                    opis = rezultat.weather.firstOrNull()?.description ?: "Ni podatka"
                    vlaznost = "Vlažnost: ${rezultat.main.humidity} %"
                } catch (e: Exception) {
                    temperatura = "Napaka: ${e.message}"
                }
            }

            Column(modifier = Modifier.statusBarsPadding().padding(16.dp)) {
                Text("Temperatura: $temperatura")
                Text("Vreme: $opis")
                Text(vlaznost)
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