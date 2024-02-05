package com.musalasoft.weather.data.repository

import com.musalasoft.core.session.data.local.SessionLocal
import com.musalasoft.weather.domain.repository.WeatherRepository
import com.musalasoft.core.utilities.network.ResponseResource
import com.musalasoft.core.utilities.network.userErrorMessage
import com.musalasoft.weather.data.remote.CurrentWeatherRemote
import com.musalasoft.weather.domain.mapper.toCurrentWeather
import com.musalasoft.weather.domain.mapper.toRequestDto
import com.musalasoft.weather.domain.model.CurrentWeather
import com.musalasoft.weather.domain.model.CurrentWeatherRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class WeatherRepositoryImpl (
    private val remote: CurrentWeatherRemote,
    private val session: SessionLocal
) : WeatherRepository {

    override suspend fun getCurrentWeather(request: CurrentWeatherRequest): Flow<ResponseResource<CurrentWeather>> =
        flow {
            remote.getCurrentWeather(request.toRequestDto()).catch {
                emit(ResponseResource.error(it.userErrorMessage()))
            }.collect {
                val currentWeather = it.toCurrentWeather()
                emit(ResponseResource.success(currentWeather))
            }
        }
}
