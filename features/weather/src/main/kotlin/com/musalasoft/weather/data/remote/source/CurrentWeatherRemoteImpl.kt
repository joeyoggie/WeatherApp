package com.musalasoft.weather.data.remote.source

import com.musalasoft.core.session.data.local.SessionLocal
import com.musalasoft.core.utilities.network.performCall
import com.musalasoft.weather.data.remote.CurrentWeatherRemote
import com.musalasoft.weather.common.WeatherConstants
import com.musalasoft.weather.data.remote.dto.currentWeather.request.CurrentWeatherRequestDto
import com.musalasoft.weather.data.remote.dto.currentWeather.response.CurrentWeatherResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.flow.flow

class CurrentWeatherRemoteImpl(
    private val client: HttpClient,
    private val session: SessionLocal
) : CurrentWeatherRemote {

    override suspend fun getCurrentWeather(request: CurrentWeatherRequestDto) = flow {
        emit(
            runCatching {
                val response =
                    client.get {
                        parameter("lat", request.lat)
                        parameter("lon", request.lon)
                        parameter("appid", WeatherConstants.API_TOKEN)
                        parameter("units", "metric")
                        parameter("lang", session.appLanguage)
                        performCall(
                            baseUrl = WeatherConstants.OPEN_WEATHER_BASE_URL,
                            path = WeatherConstants.GET_WEATHER,
                            acceptLanguage = session.appLanguage
                        )
                    }.body<CurrentWeatherResponseDto>()
                response
            }.getOrElse { error ->
                throw error
            }
        )
    }
}
