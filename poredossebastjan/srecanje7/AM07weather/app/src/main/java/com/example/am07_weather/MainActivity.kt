package com.example.am07_weather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.am07_weather.model.WeatherResponse
import com.example.am07_weather.network.WeatherApi
import com.example.am07_weather.ui.theme.AM07weatherTheme
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Date
import java.util.Locale
import coil.compose.AsyncImage
import java.text.SimpleDateFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            var temperature by remember { mutableStateOf("") }
            var description by remember { mutableStateOf("") }
            var selectedCity by remember {
                mutableStateOf("Murska Sobota")
            }
            var wind by remember { mutableStateOf("") }
            var humidity by remember { mutableStateOf("") }
            var sunrise by remember { mutableStateOf("") }
            var sunset by remember { mutableStateOf("") }
            var windDirection by remember { mutableStateOf("") }


            val currentDateTime = SimpleDateFormat(
                "dd.MM.yyyy | HH:mm",
                Locale.getDefault()
            ).format(Date())
            var icon by remember { mutableStateOf("") }

            LaunchedEffect(selectedCity) {

                val result: WeatherResponse = RetrofitInstance.api.getWeather(
                    city = selectedCity,
                    apiKey = "45669859328735c182dd1f61da1a5c48"
                )

                temperature = "${result.main.temp.toInt()} °C"
                description = "Vreme: ${result.weather.firstOrNull()?.description ?: "Ni podatka"}"
                icon = result.weather.firstOrNull()?.icon ?: ""
                wind = "${result.wind.speed} m/s"
                humidity = "${result.main.humidity}%"
                val timeFormat = SimpleDateFormat(
                    "HH:mm",
                    Locale.getDefault()
                )

                sunrise = timeFormat.format(
                    Date(result.sys.sunrise * 1000)
                )

                sunset = timeFormat.format(
                    Date(result.sys.sunset * 1000)
                )

                windDirection = when (result.wind.deg) {
                    in 0..22, in 338..360 -> "S"
                    in 23..67 -> "SV"
                    in 68..112 -> "V"
                    in 113..157 -> "JV"
                    in 158..202 -> "J"
                    in 203..247 -> "JZ"
                    in 248..292 -> "Z"
                    else -> "SZ"
                }
            }

            Column(
                modifier = Modifier

            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 35.dp),
                    horizontalArrangement = Arrangement.Center
                ) {

                    Button(
                        onClick = { selectedCity = "Ljubljana" },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF0A5C93)
                        )
                    ) {
                        Text("Ljubljana")
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        onClick = { selectedCity = "Maribor" },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF0A5C93)
                        )
                    ) {
                        Text("Maribor")
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        onClick = { selectedCity = "Murska Sobota" },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF0A5C93)
                        )
                    ) {
                        Text("Murska Sobota")
                    }
                }


                Vreme(
                    city = selectedCity,
                    temp = temperature,
                    description = description,
                    dateTime = currentDateTime,
                    icon = icon,
                    wind = wind,
                    windDirection = windDirection,
                    humidity = humidity,
                    sunrise = sunrise,
                    sunset = sunset
                )
            }




        }
    }
}

@Composable
fun Vreme(
    temp: String,
    description: String,
    city: String,
    dateTime: String,
    icon: String,
    wind: String,
    windDirection: String,
    humidity: String,
    sunrise: String,
    sunset: String,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFF0B6AA8),
                        Color(0xFF177BB8)
                    )
                )
            )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Spacer(modifier = Modifier.height(5.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Column(
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = dateTime,
                        color = Color.LightGray,
                        fontSize = 15.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = city,
                        color = Color.White,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = description.replace("Vreme: ", ""),
                        color = Color.White,
                        fontSize = 24.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))


            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 24.dp),
                color = Color.White,
                thickness = 3.dp
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = temp,
                    color = Color.White,
                    fontSize = 60.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.width(20.dp))

                AsyncImage(
                    model = "https://openweathermap.org/img/wn/${icon}@2x.png",
                    contentDescription = null,
                    modifier = Modifier.size(170.dp)
                )
            }

            Spacer(modifier = Modifier.height(5.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {

                Column {

                    Row(
                        modifier = Modifier.fillMaxWidth(0.6f),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Hitrost Vetra",
                            color = Color.LightGray
                        )

                        Text(
                            text = "$wind $windDirection",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(0.6f),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Vlažnost",
                            color = Color.LightGray
                        )

                        Text(
                            text = humidity,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(40.dp))


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Vzhod $sunrise", color = Color.White)
                Text("Zahod $sunset", color = Color.White)
            }

            Spacer(modifier = Modifier.height(15.dp))


            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 24.dp),
                color = Color.White,
                thickness = 3.dp
            )
        }
    }
}




object RetrofitInstance {

    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

    val api: WeatherApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }
}