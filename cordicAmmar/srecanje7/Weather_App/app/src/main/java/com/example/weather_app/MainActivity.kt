package com.example.weather_app

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather_app.model.ForecastResponse
import com.example.weather_app.model.ForecastWeather
import com.example.weather_app.model.WeatherResponse
import com.example.weather_app.network.WeatherApi
import com.example.weather_app.ui.theme.Weather_AppTheme
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.getValue
import java.util.Locale
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Column (
                modifier = Modifier.statusBarsPadding()
            ) {

                Screen()

            }

        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Screen(modifier: Modifier = Modifier) {
    Column (modifier = Modifier
        .fillMaxSize()
        .statusBarsPadding()
        .background(Color.Blue)) {
        val locationList = listOf<String>("Maribor", "Ljubljana", "Koper")
        var chosenLocation by remember { mutableStateOf(locationList.first()) }

        val dayList = listOf<String>("Danes", "Jutri")
        var chosenDay by remember { mutableStateOf(dayList.first()) }

        var weatherData by remember { mutableStateOf<WeatherResponse?>(null) }
        var forecastData by remember { mutableStateOf<ForecastResponse?>(null) }

        LaunchedEffect(chosenLocation) {
            weatherData = RetrofitInstance.api.getWeather(
                city = chosenLocation,
                apiKey = "fa182ea79f060a342cf68ee94f94cc4a"
            )

            forecastData = RetrofitInstance.api.getForecast(
                city = chosenLocation,
                apiKey = "fa182ea79f060a342cf68ee94f94cc4a"
            )

        }

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0x3B9AFFFF))
        ) {
            // Display the list of locations in a row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                locationList.forEach {
                    Text(
                        text = it,
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .weight(1f)
                            .clickable(
                                onClickLabel = "Choose $it",
                                onClick = {
                                    chosenLocation = it
                                }
                            ),
                        color = Color.White,
                        textDecoration = if (it == chosenLocation) TextDecoration.Underline else null,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = if (it == chosenLocation) FontWeight.Bold else FontWeight.Normal
                    )
                }
            }

            HorizontalDivider(
                color = Color.White,
                thickness = 1.dp
            )

            // Choose a day for showing of the forecast
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

                dayList.forEach {
                    Text(
                        text = it,
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .weight(1f)
                            .clickable(
                                onClickLabel = "Choose $it",
                                onClick = {
                                    chosenDay = it
                                }
                            ),
                        color = Color.White,
                        textDecoration = if (it == chosenDay) TextDecoration.Underline else null,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = if (it == chosenDay) FontWeight.Bold else FontWeight.Normal
                    )
                }
            }

            HorizontalDivider(
                color = Color.White,
                thickness = 1.dp
            )
        }

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0x3B9AFFFF))
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {

            Text(
                text = chosenLocation,
                modifier = Modifier.padding(4.dp),
                color = Color.White
            )
            LocalDateTime.now().let { currentDateTime ->
                Text(
                    text = currentDateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")),
                    modifier = Modifier.padding(4.dp),
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.size(20.dp))

            ShowCurrentWeather(weatherData)

            Spacer(modifier = Modifier.size(40.dp))

            ShowWeaterByHours(forecastData, chosenDay)

        }

    }
}

@Composable
fun ShowCurrentWeather(currentWeather: WeatherResponse? = null, modifier: Modifier = Modifier) {
    Column {
        Text (
            text = currentWeather?.weather?.firstOrNull()?.description ?: "N/A",
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color.White
        )

        Spacer(modifier = Modifier.size(12.dp))

        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "${currentWeather?.main?.temp?.roundToInt() ?: "N/A"} °C",
                modifier = Modifier
                    .padding(4.dp)
                    .weight(1f),
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )

            Image(
                painter = painterResource(
                    id = when (currentWeather?.weather?.firstOrNull()?.main) {
                        "Clear" -> R.drawable.sun
                        "Clouds" -> R.drawable.clouds
                        "Rain" -> R.drawable.heavyrain
                        "Snow" -> R.drawable.snowy
                        "Fog" -> R.drawable.haze
                        else -> R.drawable.unknown
                    }
                ),
                contentDescription = "Weather Icon",
                modifier = Modifier
                    .padding(4.dp)
                    .size(64.dp)
                    .weight(1f)
            )
        }

        Text(
            text = "Hitrost vetra: ${currentWeather?.wind?.speed?.times(3.6)?.roundToInt() } km/h",
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            color = Color.White,
            textAlign = TextAlign.Center
        )

        Text(
            text = "Smer vetra: ${windDirection(currentWeather?.wind?.deg ?: 361)}",
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            color = Color.White,
            textAlign = TextAlign.Center
        )

        Text(
            text = "Vlažnost: ${currentWeather?.main?.humidity ?: "N/A"} %",
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ShowWeaterByHours(forecastWeather: ForecastResponse? = null, chosenDay: String, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = "Vremenska napoved po urah",
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.White
        )

        forecastWeather?.list?.forEach { forecast ->
            if (chosenDay == "Danes" && forecast.dt_txt.substring(0..9) != LocalDate.now().toString()) return@forEach
            if (chosenDay == "Jutri" && forecast.dt_txt.substring(0..9) != LocalDate.now().plusDays(1).toString()) return@forEach
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Text(
                    text = forecast.dt_txt.substring(11..15),
                    modifier = Modifier.padding(4.dp).weight(2f),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )

                Text(
                    text = "${forecast.main.temp.roundToInt()} °C",
                    modifier = Modifier.padding(4.dp).weight(1f),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )

                Image(
                    painter = painterResource(
                        id = when (forecast.weather.firstOrNull()?.main) {
                            "Clear" -> R.drawable.sun
                            "Clouds" -> R.drawable.clouds
                            "Rain" -> R.drawable.heavyrain
                            "Snow" -> R.drawable.snowy
                            "Fog" -> R.drawable.haze
                            else -> R.drawable.unknown
                        }
                    ),
                    contentDescription = "Weather Icon",
                    modifier = Modifier
                        .padding(4.dp)
                        .size(32.dp)
                        .weight(1f)
                )

                Text(
                    text = forecast.wind.speed.times(3.6).roundToInt().toString() + " km/h",
                    modifier = Modifier.padding(4.dp).weight(1f),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }
    }

}

fun windDirection(degrees: Int): String {
    return when (degrees) {
        in 0..22 -> "Sever"
        in 23..67 -> "Severovzhod"
        in 68..112 -> "Vzhod"
        in 113..157 -> "Jugovzhod"
        in 158..202 -> "Jug"
        in 203..247 -> "Jugozahod"
        in 248..292 -> "Zahod"
        in 293..337 -> "Severozahod"
        in 338..360 -> "Sever"
        else -> "Neznano"
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
