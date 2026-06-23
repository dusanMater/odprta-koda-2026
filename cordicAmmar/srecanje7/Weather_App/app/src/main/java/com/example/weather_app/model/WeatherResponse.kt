package com.example.weather_app.model

data class WeatherResponse(
    val name: String,
    val weather: List<Weather>,
    val main: Main,
    val wind: Wind,
    val sys: Sys,
    val dt: Long
)

data class Main(
    val temp: Double,
    val humidity: Int
)

data class Weather(
    val main: String,
    val description: String
)

data class Wind(
    val speed: Double,
    val deg: Int
)

data class Sys(
    val sunrise: Long,
    val sunset: Long
)