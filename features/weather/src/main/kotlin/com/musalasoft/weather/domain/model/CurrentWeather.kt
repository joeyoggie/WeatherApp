package com.musalasoft.weather.domain.model

data class CurrentWeather(
    val main: String,
    val description: String,
    val temperature: Double,
    val feelsLike: Double,
    val temperatureMin: Double,
    val temperatureMax: Double,
    val humidity: Double,
    val visibility: Double,
    val windSpeed: Double,
    val rain: Double,
    val clouds: Double
)
