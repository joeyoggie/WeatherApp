package com.musalasoft.weather.data.remote.dto.currentWeather.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeatherRequestDto(
    @SerialName("lat")
    val lat: Double?,
    @SerialName("lon")
    val lon: Double?
)