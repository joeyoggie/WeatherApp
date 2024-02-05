package com.musalasoft.weather.data.remote.dto.currentWeather.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeatherResponseDto(
    @SerialName("coord")
    val coord: Coord?,
    @SerialName("weather")
    val weather: List<Weather?>?,
    @SerialName("base")
    val base: String?,
    @SerialName("main")
    val main: Main?,
    @SerialName("visibility")
    val visibility: Double?,
    @SerialName("wind")
    val wind: Wind?,
    @SerialName("rain")
    val rain: Rain?,
    @SerialName("clouds")
    val clouds: Clouds?,
    @SerialName("dt")
    val dt: Double?,
    @SerialName("sys")
    val sys: Sys?,
    @SerialName("timezone")
    val timezone: Double?,
    @SerialName("id")
    val id: Double?,
    @SerialName("name")
    val name: String?,
    @SerialName("cod")
    val cod: Double?
) {
    @Serializable
    data class Coord(
        @SerialName("lon")
        val lon: Double?,
        @SerialName("lat")
        val lat: Double?
    )

    @Serializable
    data class Weather(
        @SerialName("id")
        val id: Double?,
        @SerialName("main")
        val main: String?,
        @SerialName("description")
        val description: String?,
        @SerialName("icon")
        val icon: String?
    )

    @Serializable
    data class Main(
        @SerialName("temp")
        val temp: Double?,
        @SerialName("feels_like")
        val feelsLike: Double?,
        @SerialName("temp_min")
        val tempMin: Double?,
        @SerialName("temp_max")
        val tempMax: Double?,
        @SerialName("pressure")
        val pressure: Double?,
        @SerialName("humidity")
        val humidity: Double?,
        @SerialName("sea_level")
        val seaLevel: Double?,
        @SerialName("grnd_level")
        val grndLevel: Double?
    )

    @Serializable
    data class Wind(
        @SerialName("speed")
        val speed: Double?,
        @SerialName("deg")
        val deg: Double?,
        @SerialName("gust")
        val gust: Double?
    )

    @Serializable
    data class Rain(
        @SerialName("1h")
        val h: Double?
    )

    @Serializable
    data class Clouds(
        @SerialName("all")
        val all: Double?
    )

    @Serializable
    data class Sys(
        @SerialName("type")
        val type: Double?,
        @SerialName("id")
        val id: Double?,
        @SerialName("country")
        val country: String?,
        @SerialName("sunrise")
        val sunrise: Double?,
        @SerialName("sunset")
        val sunset: Double?
    )
}