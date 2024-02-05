package com.musalasoft.weather.presentation.data

import com.musalasoft.location.domain.model.City

sealed class WeatherEvent {
    data object GetCurrentUserLocation : WeatherEvent()
    data object UserDismissResolvableException : WeatherEvent()
    data class SearchForCity(val city: String) : WeatherEvent()
    data class GetWeatherForCity(val city: City) : WeatherEvent()
}
