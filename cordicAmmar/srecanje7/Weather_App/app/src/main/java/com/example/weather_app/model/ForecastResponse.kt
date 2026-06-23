package com.example.weather_app.model

data class ForecastResponse(
    val list: List<ForecastItem>,
    val city: City
)

data class ForecastItem(
    val dt: Long,
    val main: ForecastMain,
    val weather: List<ForecastWeather>,
    val wind: ForecastWind,
    val dt_txt: String
)

data class ForecastMain(
    val temp: Double,
    val feels_like: Double,
    val humidity: Int
)

data class ForecastWeather(
    val main: String,
    val description: String
)

data class ForecastWind(
    val speed: Double,
    val deg: Int
)

data class City(
    val name: String,
    val country: String
)