package com.musalasoft.weather.presentation.data


data class UiWeatherItem(
    val main: String,
    val description: String,
    val temperature: String,
    val feelsLike: String,
    val temperatureMin: String,
    val temperatureMax: String,
    val humidity: String,
    val visibility: String,
    val windSpeed: String,
    val rain: String,
    val clouds: String
)
