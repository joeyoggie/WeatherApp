package com.musalasoft.weather.data.remote

import com.musalasoft.weather.data.remote.dto.currentWeather.request.CurrentWeatherRequestDto
import com.musalasoft.weather.data.remote.dto.currentWeather.response.CurrentWeatherResponseDto
import kotlinx.coroutines.flow.Flow

interface CurrentWeatherRemote {
    suspend fun getCurrentWeather(request: CurrentWeatherRequestDto): Flow<CurrentWeatherResponseDto>
}
