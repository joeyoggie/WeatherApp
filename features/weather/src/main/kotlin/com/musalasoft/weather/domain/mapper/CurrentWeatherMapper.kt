package com.musalasoft.weather.domain.mapper

import com.musalasoft.core.utilities.constants.Constants
import com.musalasoft.weather.data.remote.dto.currentWeather.request.CurrentWeatherRequestDto
import com.musalasoft.weather.data.remote.dto.currentWeather.response.CurrentWeatherResponseDto
import com.musalasoft.weather.domain.model.CurrentWeather
import com.musalasoft.weather.domain.model.CurrentWeatherRequest

fun CurrentWeatherRequest.toRequestDto() = CurrentWeatherRequestDto(
    lat = this.latitude,
    lon = this.longitude
)

fun CurrentWeatherResponseDto.toCurrentWeather() = CurrentWeather(
    main = this.weather?.firstOrNull()?.main ?: Constants.EMPTY_STRING_PLACEHOLDER,
    description = this.weather?.firstOrNull()?.description ?: Constants.EMPTY_STRING_PLACEHOLDER,
    temperature = this.main?.temp ?: Constants.UNKNOWN_DOUBLE,
    feelsLike = this.main?.feelsLike ?: Constants.UNKNOWN_DOUBLE,
    temperatureMin = this.main?.tempMin ?: Constants.UNKNOWN_DOUBLE,
    temperatureMax = this.main?.tempMax ?: Constants.UNKNOWN_DOUBLE,
    humidity = this.main?.humidity ?: Constants.UNKNOWN_DOUBLE,
    visibility = this.visibility ?: Constants.UNKNOWN_DOUBLE,
    windSpeed = this.wind?.speed ?: Constants.UNKNOWN_DOUBLE,
    rain = this.rain?.h ?: Constants.UNKNOWN_DOUBLE,
    clouds = this.clouds?.all ?: Constants.UNKNOWN_DOUBLE,
)
