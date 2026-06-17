package com.example.vreme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vreme.ui.theme.VremeTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VremeTheme {
                WeatherApp()
            }
        }
    }
}

data class City(val name: String, val latitude: Double, val longitude: Double)

data class WeatherData(
    val city: City,
    val measuredAt: String,
    val description: String,
    val temperature: Double,
    val apparentTemperature: Double,
    val humidity: Int,
    val windSpeed: Double,
    val windDirection: Int,
    val windText: String,
    val visibility: Int,
    val pressure: Double,
    val sunrise: String,
    val sunset: String,
    val icon: WeatherIcon
)

enum class WeatherIcon { Sun, Cloud, Rain, Snow, Storm, Fog }

private val cities = listOf(
    City("Ljubljana", 46.0569, 14.5058),
    City("Maribor", 46.5547, 15.6459),
    City("Ptuj", 46.4199, 15.8702),
    City("Celje", 46.2397, 15.2677),
    City("Koper", 45.5481, 13.7302),
    City("Novo mesto", 45.8039, 15.1689)
)

@Composable
fun WeatherApp() {
    var selectedCity by remember { mutableStateOf(cities.first()) }
    var weather by remember { mutableStateOf<WeatherData?>(null) }
    var error by remember { mutableStateOf<String?>(null) }
    var loading by remember { mutableStateOf(false) }

    LaunchedEffect(selectedCity) {
        loading = true
        error = null
        weather = runCatching { loadWeather(selectedCity) }
            .onFailure { error = "Podatkov ni bilo mogoče naložiti." }
            .getOrNull()
        loading = false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0B83B7))
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
    ) {
        WeatherTopBar(selectedCity = selectedCity, onCitySelected = { selectedCity = it })

        when {
            loading -> LoadingContent()
            error != null -> ErrorContent(error = error.orEmpty())
            weather != null -> WeatherContent(weather = weather!!)
        }
    }
}

@Composable
fun WeatherTopBar(selectedCity: City, onCitySelected: (City) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF0A92C8))
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Vreme", color = Color.White.copy(alpha = 0.8f), fontSize = 13.sp)
        Box {
            Button(
                onClick = { expanded = true },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0876A8)),
                shape = RoundedCornerShape(6.dp)
            ) {
                Text(selectedCity.name, color = Color.White, fontSize = 18.sp)
            }
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                cities.forEach { city ->
                    DropdownMenuItem(
                        text = { Text(city.name) },
                        onClick = {
                            expanded = false
                            onCitySelected(city)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun LoadingContent() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(420.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = Color.White)
    }
}

@Composable
fun ErrorContent(error: String) {
    Text(
        text = error,
        color = Color.White,
        modifier = Modifier.padding(24.dp),
        fontSize = 18.sp
    )
}

@Composable
fun WeatherContent(weather: WeatherData) {
    Column(modifier = Modifier.padding(18.dp)) {
        Text(weather.measuredAt, color = Color.White.copy(alpha = 0.72f), fontSize = 12.sp)
        Text(weather.description, color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.Top) {
                Text(
                    weather.temperature.roundToInt().toString(),
                    color = Color.White,
                    fontSize = 64.sp,
                    fontWeight = FontWeight.Bold
                )
                Text("°C", color = Color.White.copy(alpha = 0.75f), modifier = Modifier.padding(top = 16.dp))
            }
            WeatherIconView(icon = weather.icon, modifier = Modifier.size(118.dp))
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 26.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            WeatherStat("Občutek", "${weather.apparentTemperature.roundToInt()} °C")
            WeatherStat("Vlažnost", "${weather.humidity} %")
            WeatherStat("Tlak", "${weather.pressure.roundToInt()} hPa")
        }

        Spacer(modifier = Modifier.height(18.dp))

        Surface(
            color = Color.White.copy(alpha = 0.12f),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                DetailRow("Veter", "${weather.windSpeed.roundToInt()} km/h, ${weather.windDirection}° ${weather.windText}")
                DetailRow("Vidljivost", "${weather.visibility / 1000.0} km")
                DetailRow("Sončni vzhod", weather.sunrise)
                DetailRow("Sončni zahod", weather.sunset)
            }
        }
    }
}

@Composable
fun WeatherStat(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(label, color = Color.White.copy(alpha = 0.65f), fontSize = 12.sp)
        Text(value, color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun DetailRow(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, color = Color.White.copy(alpha = 0.7f))
        Text(value, color = Color.White, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
fun WeatherIconView(icon: WeatherIcon, modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.clip(RoundedCornerShape(12.dp))) {
        val c = center
        when (icon) {
            WeatherIcon.Sun -> {
                drawCircle(Color(0xFFFFD166), radius = size.minDimension * 0.24f, center = c)
                drawCircle(Color(0x55FFFFFF), radius = size.minDimension * 0.38f, center = c, style = Stroke(16f))
            }
            WeatherIcon.Cloud -> {
                drawCircle(Color(0xFFBFD7EA), radius = size.minDimension * 0.22f, center = Offset(c.x - 20f, c.y))
                drawCircle(Color(0xFFDDE9F2), radius = size.minDimension * 0.28f, center = Offset(c.x + 16f, c.y - 12f))
                drawOval(Color(0xFFEAF3F8), topLeft = Offset(c.x - 48f, c.y), size = Size(96f, 36f))
            }
            WeatherIcon.Rain -> {
                drawCircle(Color(0xFFDDE9F2), radius = size.minDimension * 0.26f, center = Offset(c.x, c.y - 18f))
                drawOval(Color(0xFFEAF3F8), topLeft = Offset(c.x - 48f, c.y - 8f), size = Size(96f, 40f))
                repeat(3) { i ->
                    val x = c.x - 30f + i * 30f
                    drawLine(Color(0xFF8ECAE6), Offset(x, c.y + 42f), Offset(x - 8f, c.y + 66f), strokeWidth = 6f, cap = StrokeCap.Round)
                }
            }
            WeatherIcon.Snow -> {
                drawCircle(Color(0xFFEAF3F8), radius = size.minDimension * 0.28f, center = Offset(c.x, c.y - 18f))
                drawOval(Color.White, topLeft = Offset(c.x - 50f, c.y - 5f), size = Size(100f, 42f))
                repeat(3) { i -> drawCircle(Color.White, radius = 6f, center = Offset(c.x - 30f + i * 30f, c.y + 60f)) }
            }
            WeatherIcon.Storm -> {
                drawOval(Color(0xFFEAF3F8), topLeft = Offset(c.x - 48f, c.y - 20f), size = Size(96f, 42f))
                drawPath(
                    androidx.compose.ui.graphics.Path().apply {
                        moveTo(c.x - 4f, c.y + 15f)
                        lineTo(c.x - 22f, c.y + 54f)
                        lineTo(c.x + 2f, c.y + 48f)
                        lineTo(c.x - 12f, c.y + 82f)
                        lineTo(c.x + 28f, c.y + 34f)
                        lineTo(c.x + 6f, c.y + 39f)
                        close()
                    },
                    Color(0xFFFFD166)
                )
            }
            WeatherIcon.Fog -> {
                repeat(4) { i ->
                    val y = c.y - 28f + i * 20f
                    drawLine(Color(0xFFEAF3F8), Offset(c.x - 48f, y), Offset(c.x + 48f, y), strokeWidth = 8f, cap = StrokeCap.Round)
                }
            }
        }
    }
}

suspend fun loadWeather(city: City): WeatherData = withContext(Dispatchers.IO) {
    val url = "https://api.open-meteo.com/v1/forecast" +
        "?latitude=${city.latitude}&longitude=${city.longitude}" +
        "&current=temperature_2m,relative_humidity_2m,apparent_temperature,weather_code,surface_pressure,visibility,wind_speed_10m,wind_direction_10m" +
        "&daily=sunrise,sunset&timezone=auto"

    val json = JSONObject(URL(url).readText())
    val current = json.getJSONObject("current")
    val daily = json.getJSONObject("daily")
    val code = current.getInt("weather_code")
    val weatherKind = weatherKind(code)

    WeatherData(
        city = city,
        measuredAt = "${city.name}, ${formatTime(current.getString("time"))}",
        description = weatherKind.first,
        temperature = current.getDouble("temperature_2m"),
        apparentTemperature = current.getDouble("apparent_temperature"),
        humidity = current.getInt("relative_humidity_2m"),
        windSpeed = current.getDouble("wind_speed_10m"),
        windDirection = current.getInt("wind_direction_10m"),
        windText = windDirectionText(current.getInt("wind_direction_10m")),
        visibility = current.getInt("visibility"),
        pressure = current.getDouble("surface_pressure"),
        sunrise = formatTime(daily.getJSONArray("sunrise").getString(0)),
        sunset = formatTime(daily.getJSONArray("sunset").getString(0)),
        icon = weatherKind.second
    )
}

fun weatherKind(code: Int): Pair<String, WeatherIcon> = when (code) {
    0 -> "Jasno" to WeatherIcon.Sun
    1, 2 -> "Pretežno jasno" to WeatherIcon.Cloud
    3 -> "Oblačno" to WeatherIcon.Cloud
    45, 48 -> "Megla" to WeatherIcon.Fog
    51, 53, 55, 56, 57, 61, 63, 65, 66, 67, 80, 81, 82 -> "Dež" to WeatherIcon.Rain
    71, 73, 75, 77, 85, 86 -> "Sneg" to WeatherIcon.Snow
    95, 96, 99 -> "Nevihta" to WeatherIcon.Storm
    else -> "Vremenski podatki" to WeatherIcon.Cloud
}

fun windDirectionText(degrees: Int): String = when (((degrees + 22) % 360) / 45) {
    0 -> "S"
    1 -> "SV"
    2 -> "V"
    3 -> "JV"
    4 -> "J"
    5 -> "JZ"
    6 -> "Z"
    else -> "SZ"
}

fun formatTime(value: String): String = value.substringAfter("T").take(5)
