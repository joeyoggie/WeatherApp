package com.musalasoft.weather.presentation.data

import com.musalasoft.weather.domain.model.CurrentWeather
import kotlin.math.roundToInt

fun CurrentWeather.toUiWeatherItem() = UiWeatherItem(
    main = this.main,
    description = this.description,
    temperature = this.temperature.roundToInt().toString().plus("°C"),
    feelsLike = this.feelsLike.roundToInt().toString().plus("°C"),
    temperatureMin = this.temperature.roundToInt().toString().plus("°C"),
    temperatureMax = this.temperature.roundToInt().toString().plus("°C"),
    humidity = this.humidity.toString().plus("%"),
    visibility = this.visibility.toString().plus(" KM"),
    windSpeed = this.windSpeed.toString().plus(" KM/H"),
    rain = this.rain.toString().plus("%"),
    clouds = this.clouds.toString()
)
