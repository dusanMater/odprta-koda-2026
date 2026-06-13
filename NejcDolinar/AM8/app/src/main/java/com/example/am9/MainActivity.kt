package com.example.am9

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.google.gson.annotations.SerializedName
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Display()
            }
        }
    }
}

// --- Data classes ---
data class WeatherResponse(
    val name: String,
    val weather: List<WeatherDesc>,
    val main: MainData,
    val wind: WindData,
    val sys: SysData,
    val dt: Long
)

data class WeatherDesc(
    val description: String,
    val icon: String
)

data class MainData(
    val temp: Double,
    val humidity: Int
)

data class WindData(
    val speed: Double,
    val deg: Int
)

data class SysData(
    val sunrise: Long,
    val sunset: Long
)

// --- Retrofit ---
interface WeatherApi {
    @GET("weather")
    suspend fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "sl"
    ): WeatherResponse
}

val retrofit = Retrofit.Builder()
    .baseUrl("https://api.openweathermap.org/data/2.5/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val weatherApi: WeatherApi = retrofit.create(WeatherApi::class.java)

// --- Helpers ---
fun windDirection(deg: Int): String {
    return when (deg) {
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

fun windDescription(speed: Double): String {
    return when {
        speed < 1 -> "Tišina"
        speed < 6 -> "Lahek veter"
        speed < 12 -> "Zmeren veter"
        speed < 20 -> "Močan veter"
        else -> "Vihar"
    }
}

fun formatTime(unix: Long): String {
    val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
    return sdf.format(Date(unix * 1000))
}

data class Kraj(val name: String, val lat: Double, val lon: Double)

// --- Main composable ---
@Composable
fun Display() {
    val API_KEY = "578f30ff4165eda3e82923126a31f982"

    val kraji = listOf(
        Kraj("Ljubljana", 46.0569, 14.5058),
        Kraj("Maribor", 46.5547, 15.6459),
        Kraj("Koper", 45.5469, 13.7297)
    )

    var selectedKraj by remember { mutableStateOf(kraji[0]) }
    var weatherData by remember { mutableStateOf<WeatherResponse?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(selectedKraj) {
        isLoading = true
        error = null
        try {
            weatherData = weatherApi.getWeather(selectedKraj.lat, selectedKraj.lon, API_KEY)
        } catch (e: Exception) {
            error = "Napaka pri pridobivanju podatkov"
        }
        isLoading = false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        // Location selector
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            kraji.forEach { kraj ->
                Button(onClick = { selectedKraj = kraj }) {
                    Text(kraj.name)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        when {
            isLoading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            error != null -> {
                Text(error!!, color = Color.Red)
            }
            weatherData != null -> {
                val data = weatherData!!

                // Location + time
                Text(data.name, fontSize = 28.sp, fontWeight = FontWeight.Bold)
                Text(
                    "Čas meritve: ${formatTime(data.dt)}",
                    fontSize = 12.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Weather icon
                AsyncImage(
                    model = "https://openweathermap.org/img/wn/${data.weather[0].icon}@2x.png",
                    contentDescription = "Ikona vremena",
                    modifier = Modifier.size(100.dp)
                )

                // Description
                Text(
                    data.weather[0].description,
                    fontSize = 18.sp
                )

                // Temperature
                Text(
                    "${data.main.temp}°C",
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Wind
                Text(
                    "Veter: ${data.wind.speed} m/s ${windDirection(data.wind.deg)} — ${windDescription(data.wind.speed)}",
                    fontSize = 14.sp
                )

                // Humidity
                Text(
                    "Vlažnost: ${data.main.humidity}%",
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Sunrise / Sunset
                Text("🌅 Vzhod: ${formatTime(data.sys.sunrise)}", fontSize = 14.sp)
                Text("🌇 Zahod: ${formatTime(data.sys.sunset)}", fontSize = 14.sp)
            }
        }
    }
}