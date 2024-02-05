package com.musalasoft.weather.domain.repository

import com.musalasoft.core.utilities.network.ResponseResource
import com.musalasoft.weather.domain.model.CurrentWeather
import com.musalasoft.weather.domain.model.CurrentWeatherRequest
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getCurrentWeather(request: CurrentWeatherRequest): Flow<ResponseResource<CurrentWeather>>
}
