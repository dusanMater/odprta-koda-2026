package com.example.predavanje7vaja1

data class WeatherResponse(
    val name: String,
    val dt: Long,
    val main: Main,
    val weather: List<Weather>,
    val wind: Wind,
    val sys: Sys
)
data class Main(
    val temp: Double,
    val humidity: Int
)
data class Weather(
    val main: String,
    val description: String,
    val icon: String
)
data class Wind(
    val speed: Double,
    val deg: Int
)
data class Sys(
    val sunrise: Long,
    val sunset: Long
)

data class ForecastResponse(
    val list: List<ForecastData>
)
data class ForecastData(
    val main: Main,
    val weather: List<Weather>,
    val wind: Wind,
    val dt_txt: String
)