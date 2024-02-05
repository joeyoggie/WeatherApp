package com.musalasoft.weather.presentation.data

import com.musalasoft.core.utilities.error.UiError
import com.musalasoft.core.utilities.network.AuthenticationException
import com.musalasoft.core.utilities.view.CustomViewState
import com.musalasoft.location.domain.model.City
import com.musalasoft.location.domain.model.Location

data class WeatherState(
    val data: UiWeatherItem? = null,
    val showCities: Boolean = false,
    val cities: List<City> ? = null,
    val isCitiesLoading: Boolean = false,
    val isLocationLoading: Boolean = false,
    val location: Location? = null,
    val fullAddressText: String? = null,
    val city: String? = null,
    val country: String? = null,
    val locationServicesTurnedOff: Boolean = false,
    val exception: Throwable? = null,
    override val authException: AuthenticationException? = null,
    override val error: UiError? = null,
    override val isEmpty: Boolean = false,
    override val isLoading: Boolean = false
) : CustomViewState
