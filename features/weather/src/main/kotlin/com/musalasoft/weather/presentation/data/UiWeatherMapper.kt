package com.musalasoft.weather.presentation.data

import com.musalasoft.core.utilities.constants.Constants
import com.musalasoft.weather.domain.model.CurrentWeather
import kotlin.math.roundToInt

fun CurrentWeather.toUiWeatherItem() = UiWeatherItem(
    main = this.main,
    description = this.description,
    temperature = if (this.temperature != Constants.UNKNOWN_DOUBLE) {
        this.temperature.roundToInt().toString().plus("°C")
    } else {
        Constants.EMPTY_STRING_PLACEHOLDER
    },
    feelsLike = if (this.feelsLike != Constants.UNKNOWN_DOUBLE) {
        this.feelsLike.roundToInt().toString().plus("°C")
    } else {
        Constants.EMPTY_STRING_PLACEHOLDER
    },
    temperatureMin = if (this.temperatureMin != Constants.UNKNOWN_DOUBLE) {
        this.temperatureMin.roundToInt().toString().plus("°C")
    } else {
        Constants.EMPTY_STRING_PLACEHOLDER
    },
    temperatureMax = if (this.temperatureMax != Constants.UNKNOWN_DOUBLE) {
        this.temperatureMax.roundToInt().toString().plus("°C")
    } else {
        Constants.EMPTY_STRING_PLACEHOLDER
    },
    humidity = if (this.humidity != Constants.UNKNOWN_DOUBLE) {
        this.humidity.roundToInt().toString().plus("%")
    } else {
        Constants.EMPTY_STRING_PLACEHOLDER
    },
    visibility = if (this.visibility != Constants.UNKNOWN_DOUBLE) {
        this.visibility.roundToInt().toString().plus(" KM")
    } else {
        Constants.EMPTY_STRING_PLACEHOLDER
    },
    windSpeed = if (this.windSpeed != Constants.UNKNOWN_DOUBLE) {
        this.windSpeed.roundToInt().toString().plus("KM/H")
    } else {
        Constants.EMPTY_STRING_PLACEHOLDER
    },
    rain = if (this.rain != Constants.UNKNOWN_DOUBLE) {
        this.rain.roundToInt().toString().plus("%")
    } else {
        Constants.EMPTY_STRING_PLACEHOLDER
    },
    clouds = if (this.clouds != Constants.UNKNOWN_DOUBLE) {
        this.clouds.roundToInt().toString()
    } else {
        Constants.EMPTY_STRING_PLACEHOLDER
    }
)
