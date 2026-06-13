package com.example.vreme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vreme.ui.theme.VremeTheme
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.vreme.network.WeatherApi
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            var selectedCity by remember { mutableStateOf("Postojna") }
            var temperature:String by remember { mutableStateOf("") }
            var description:String by remember { mutableStateOf("") }
            var humidity:String by remember { mutableStateOf("") }
            var casMeritve by remember { mutableStateOf("") }
            var veter by remember { mutableStateOf("") }
            var vzhod by remember { mutableStateOf("") }
            var zahod by remember { mutableStateOf("") }
            var ikona by remember { mutableStateOf("") }
            var napoved by remember { mutableStateOf("") }

            LaunchedEffect(selectedCity) {
                val result: WeatherResponse = RetrofitInstance.api.getWeather(
                    city = selectedCity,
                    apiKey = "97a82261bbcbd29cf7ca511e80cfa384"
                )

                temperature = "${result.main.temp} °C"
                description = result.weather.firstOrNull()?.description ?: "Ni podatka"
                humidity = "Vlažnost: ${result.main.humidity} %"
                val forecast = RetrofitInstance.api.getForecast(
                    city = selectedCity,
                    apiKey = "97a82261bbcbd29cf7ca511e80cfa384"
                )

                val uraFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

                temperature =
                    String.format(
                        "%.1f °C",
                        result.main.temp
                    )
                description = result.weather[0].description
                humidity = "Vlažnost: ${result.main.humidity} %"
                casMeritve = "Čas meritve: ${uraFormat.format(Date(result.dt * 1000))}"
                veter = "Veter: ${result.wind.speed} m/s, ${result.wind.deg}° (${opisVetra(result.wind.deg)})"
                vzhod = "Sončni vzhod: ${uraFormat.format(Date(result.sys.sunrise * 1000))}"
                zahod = "Sončni zahod: ${uraFormat.format(Date(result.sys.sunset * 1000))}"
                ikona = ikonaVremena(result.weather[0].main)
                val jutri =
                    forecast.list[8]

                napoved = "Jutri: " + String.format("%.1f °C", jutri.main.temp) + ", " + jutri.weather[0].description
            }
            Column(
                modifier =
                    Modifier
                        .statusBarsPadding()
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF0B7FAB)),
                    horizontalArrangement =
                        Arrangement.Center
                ) {

                    Button(
                        colors =
                            ButtonDefaults.buttonColors(
                                containerColor =
                                    Color(0xFF0B7FAB)
                            ),

                        onClick = {
                            selectedCity =
                                "Postojna"
                        }
                    ) {
                        Text("Postojna")
                    }

                    Button(
                        colors =
                            ButtonDefaults.buttonColors(
                                containerColor =
                                    Color(0xFF0B7FAB)
                            ),

                        onClick = {
                            selectedCity =
                                "Ljubljana"
                        }
                    ) {
                        Text("Ljubljana")
                    }

                    Button(
                        colors =
                            ButtonDefaults.buttonColors(
                                containerColor =
                                    Color(0xFF0B7FAB)
                            ),

                        onClick = {
                            selectedCity =
                                "Maribor"
                        }
                    ) {
                        Text("Maribor")
                    }

                }
                Vreme(
                    city = selectedCity,
                    temp = temperature,
                    desc = description,
                    humidity = humidity,
                    casMeritve = casMeritve,
                    veter = veter,
                    vzhod = vzhod,
                    zahod = zahod,
                    ikona = ikona,
                    napoved = napoved
                )
        }
    }
}

@Composable
fun Vreme(
    city: String,
    temp: String,
    desc: String,
    humidity: String,
    casMeritve: String,
    veter: String,
    vzhod: String,
    zahod: String,
    ikona: String,
    napoved: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF0B7FAB))
            .padding(16.dp)
            .statusBarsPadding()
    ) {
        Text(
            text = city,
            color = Color.White,
            fontSize = 22.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = desc.replaceFirstChar { it.uppercase() },
            color = Color.White,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(){
            Text(
                text = temp.replace(" °C", ""),
                color = Color.White,
                fontSize = 72.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "°C",
                color = Color.White,
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 20.dp)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = humidity,
            color = Color.White,
            fontSize = 18.sp
        )

        Text(text = casMeritve, color = Color.White, fontSize = 16.sp)

        Text(
            text = ikona,
            fontSize = 80.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Text(text = veter, color = Color.White, fontSize = 18.sp)
        Text(text = humidity, color = Color.White, fontSize = 18.sp)
        Text(text = vzhod, color = Color.White, fontSize = 18.sp)
        Text(text = zahod, color = Color.White, fontSize = 18.sp)

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = napoved,
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}}

fun opisVetra(deg: Int): String {
    if (deg >= 315 || deg < 45) return "severni veter"
    if (deg >= 45 && deg < 135) return "vzhodni veter"
    if (deg >= 135 && deg < 225) return "južni veter"
    return "zahodni veter"
}

fun ikonaVremena(main: String): String {
    if (main == "Rain") return "🌧️"
    if (main == "Snow") return "❄️"
    if (main == "Clear") return "☀️"
    if (main == "Clouds") return "☁️"
    if (main == "Mist" || main == "Fog") return "🌫️"
    return "🌤️"
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