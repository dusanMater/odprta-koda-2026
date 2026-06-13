package com.example.am04

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.am04.model.ForecastItem
import com.example.am04.network.RetrofitInstance
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import kotlin.math.roundToInt

const val API_KEY = "82xxf86ne_commitaj_api_keyov_000xxd"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherApp()
        }
    }
}

@Composable
fun WeatherApp() {
    var selectedCity by remember { mutableStateOf("Cvetkovci") }
    var selectedTab by remember { mutableStateOf("DANES") }
    var isSearching by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var temp by remember { mutableStateOf("") }
    var humidity by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var icon by remember { mutableStateOf("") }
    var wind by remember { mutableStateOf("") }
    var measurementTime by remember { mutableStateOf("") }
    var sunrise by remember { mutableStateOf("") }
    var sunset by remember { mutableStateOf("") }
    var forecast by remember { mutableStateOf(listOf<ForecastItem>()) }
    val cities = listOf("Cvetkovci", "Maribor", "Ljubljana")

    LaunchedEffect(selectedCity) {
        val weatherResult = RetrofitInstance.api.getWeather(selectedCity, API_KEY)
        val forecastResult = RetrofitInstance.api.getForecast(selectedCity, API_KEY)

        city = weatherResult.name
        temp = "${weatherResult.main.temp.roundToInt()} °C"
        humidity = "Vlažnost: ${weatherResult.main.humidity} %"
        description = weatherResult.weather[0].description
        icon = weatherResult.weather[0].icon
        wind = "Veter: ${windDescription(weatherResult.wind.speed)} ${windDirection(weatherResult.wind.deg)}, ${weatherResult.wind.speed} m/s"
        measurementTime = "Meritev: ${formatTime(weatherResult.dt, weatherResult.timezone)}"
        sunrise = formatTime(weatherResult.sys.sunrise, weatherResult.timezone)
        sunset = formatTime(weatherResult.sys.sunset, weatherResult.timezone)
        forecast = forecastResult.list
    }

    val shownForecast = forecastForTab(forecast, selectedTab)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0D75A7))
            .statusBarsPadding()
    ) {
        if (isSearching) {
            SearchBar(
                searchText = searchText,
                cities = cities,
                onSearchChange = {
                    searchText = it
                },
                onCityClick = {
                    selectedCity = it
                    searchText = ""
                    isSearching = false
                }
            )
        } else {
            TopBar(city) {
                isSearching = true
            }
        }
        Tabs(selectedTab) {
            selectedTab = it
        }
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(24.dp)
        ) {
            Text(measurementTime, color = Color(0xFFBDE2EF), fontSize = 16.sp, modifier = Modifier.padding(top = 8.dp))
            Text(description, color = Color.White, fontSize = 24.sp, modifier = Modifier.padding(top = 12.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 28.dp)
            ) {
                Text(
                    text = temp,
                    color = Color.White,
                    fontSize = 64.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
                WeatherIcon(icon, 120)
            }
            Text(humidity, color = Color.White, fontSize = 20.sp, modifier = Modifier.padding(top = 8.dp))
            Text(wind, color = Color.White, fontSize = 18.sp, modifier = Modifier.padding(top = 8.dp))
            Text("↑ $sunrise   ↓ $sunset", color = Color(0xFFBDE2EF), fontSize = 18.sp, modifier = Modifier.padding(top = 8.dp))
        }

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            for (item in shownForecast) {
                ForecastRow(item)
            }
        }
    }
}

@Composable
fun TopBar(
    city: String,
    onSearchClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(horizontal = 18.dp)
    ) {
        Text(
            text = city,
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.align(Alignment.Center)
        )
        Text(
            text = "⌕",
            color = Color.White,
            fontSize = 40.sp,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .clickable {
                    onSearchClick()
                }
        )
    }
}

@Composable
fun SearchBar(
    searchText: String,
    cities: List<String>,
    onSearchChange: (String) -> Unit,
    onCityClick: (String) -> Unit
) {
    val filteredCities = cities.filter {
        it.contains(searchText, ignoreCase = true)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp)
    ) {
        TextField(
            value = searchText,
            onValueChange = onSearchChange,
            label = {
                Text("Išči kraj")
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        for (city in filteredCities) {
            Text(
                text = city,
                color = Color.White,
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onCityClick(city)
                    }
                    .padding(12.dp)
            )
        }
    }
}

@Composable
fun Tabs(
    selectedTab: String,
    onTabClick: (String) -> Unit
) {
    val tabs = listOf("DANES", "JUTRI", "3 DNI")

    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.fillMaxWidth()
    ) {
        for (tab in tabs) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        onTabClick(tab)
                    }
            ) {
                Text(
                    text = tab,
                    color = Color.White,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                if (selectedTab == tab) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(3.dp)
                            .background(Color.White)
                    )
                } else {
                    Spacer(modifier = Modifier.height(3.dp))
                }
            }
        }
    }
}

@Composable
fun WeatherIcon(
    icon: String,
    size: Int
) {
    val znak = when {
        icon.startsWith("01") -> "☀"
        icon.startsWith("02") -> "⛅"
        icon.startsWith("03") -> "☁"
        icon.startsWith("04") -> "☁"
        icon.startsWith("09") -> "🌧"
        icon.startsWith("10") -> "🌦"
        icon.startsWith("11") -> "⛈"
        icon.startsWith("13") -> "❄"
        icon.startsWith("50") -> "🌫"
        else -> ""
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(size.dp)
    ) {
        Text(
            text = znak,
            fontSize = (size / 2).sp
        )
    }
}

@Composable
fun ForecastRow(item: ForecastItem) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color(0xFF8DC4D9))
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 8.dp)
        ) {
            Text(
                text = item.dt_txt.substring(11, 16),
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "${item.main.temp.roundToInt()} °C",
                color = Color.White,
                fontSize = 30.sp,
                modifier = Modifier.weight(1f)
            )
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.weight(1f)
            ) {
                WeatherIcon(item.weather[0].icon, 55)
            }
        }
    }
}

fun forecastForTab(
    forecast: List<ForecastItem>,
    selectedTab: String
): List<ForecastItem> {
    return when (selectedTab) {
        "DANES" -> forecast.filter {
            it.dt_txt.startsWith(dateString(0))
        }.take(5)

        "JUTRI" -> forecast.filter {
            it.dt_txt.startsWith(dateString(1))
        }.take(5)

        else -> forecast.take(8)
    }
}

fun dateString(addDays: Int): String {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DAY_OF_YEAR, addDays)

    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return formatter.format(calendar.time)
}

fun windDirection(deg: Int): String {
    return when (deg) {
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
        speed < 1 -> "šibek"
        speed < 5 -> "zmeren"
        else -> "močan"
    }
}

fun formatTime(
    time: Long,
    timezone: Int
): String {
    val formatter = SimpleDateFormat("HH:mm", Locale.getDefault())
    formatter.timeZone = TimeZone.getTimeZone("GMT")
    val localTime = (time + timezone) * 1000
    return formatter.format(Date(localTime))
}
