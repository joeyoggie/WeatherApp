package com.musalasoft.weather.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.musalasoft.core.utilities.error.UiError
import com.musalasoft.core.utilities.network.AuthenticationException
import com.musalasoft.core.utilities.network.ResponseResource
import com.musalasoft.location.domain.model.City
import com.musalasoft.location.domain.repository.LocationRepository
import com.musalasoft.weather.domain.model.CurrentWeatherRequest
import com.musalasoft.weather.domain.repository.WeatherRepository
import com.musalasoft.weather.presentation.data.WeatherEvent
import com.musalasoft.weather.presentation.data.WeatherState
import com.musalasoft.weather.presentation.data.toUiWeatherItem
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WeatherViewModel (
    private val weatherRepository: WeatherRepository,
    private val locationRepository: LocationRepository,
) : ViewModel() {

    private val _weatherState: MutableStateFlow<WeatherState> = MutableStateFlow(WeatherState())
    val weatherState: StateFlow<WeatherState> = _weatherState.asStateFlow()

    /**
     * On event
     * This is to be called from the UI screen
     * @param event
     */
    fun onEvent(event: WeatherEvent) {
        when (event) {
            is WeatherEvent.GetCurrentUserLocation -> getCurrentUserLocation()
            is WeatherEvent.UserDismissResolvableException -> setLocationServicesTurnedOff()
            is WeatherEvent.SearchForCity -> searchForCity(event.city)
            is WeatherEvent.GetWeatherForCity -> getWeatherForCity(event.city)
        }
    }

    private fun getCurrentUserLocation() {
        _weatherState.update {
            _weatherState.value.copy(
                isLocationLoading = true
            )
        }
        viewModelScope.launch(
            context = Dispatchers.Main,
            start = CoroutineStart.DEFAULT
        ) {
            locationRepository.getCurrentLocation().collect { result ->
                when (result) {
                    is ResponseResource.Error -> {
                        _weatherState.update {
                            _weatherState.value.copy(
                                isLocationLoading = false,
                                locationServicesTurnedOff = false,
                                exception = result.error,
                                authException = if (result.error is AuthenticationException) {
                                    AuthenticationException(
                                        result.error.message,
                                        positiveAction = { dismissError() },
                                        negativeAction = { dismissError() }
                                    )
                                } else {
                                    null
                                },
                                error = if (result.error !is AuthenticationException) {
                                    UiError(
                                        errorMessage = result.error.message.orEmpty(),
                                        positiveAction = { dismissError() },
                                        negativeAction = { dismissError() }
                                    )
                                } else {
                                    null
                                }
                            )
                        }
                    }

                    is ResponseResource.Success -> {
                        _weatherState.update {
                            _weatherState.value.copy(
                                isLocationLoading = false,
                                fullAddressText = "Current Location",
                                location = result.data
                            )
                        }
                        reverseGeocodeAddress(latitude = result.data.latitude, longitude = result.data.longitude)
                        getCurrentWeather()
                    }
                }
            }
        }
    }

    private fun reverseGeocodeAddress(latitude: Double, longitude: Double) {
        _weatherState.update {
            _weatherState.value.copy(
                isLocationLoading = true
            )
        }
        viewModelScope.launch {
            locationRepository.geocodeAddress(
                latitude = latitude,
                longitude = longitude
            ).collect { result ->
                when (result) {
                    is ResponseResource.Error -> {
                        _weatherState.update {
                            _weatherState.value.copy(
                                isLocationLoading = false,
                                authException = null,
                                error = UiError(
                                    errorMessage = result.error.message.orEmpty(),
                                    positiveAction = { dismissError() },
                                    negativeAction = { dismissError() }
                                )
                            )
                        }
                    }

                    is ResponseResource.Success -> {
                        _weatherState.update {
                            _weatherState.value.copy(
                                isLocationLoading = false,
                                city = result.data.city,
                                country = result.data.country,
                                fullAddressText = result.data.fullAddressText
                            )
                        }
                    }
                }
            }
        }
    }

    private fun getCurrentWeather() {
        _weatherState.update {
            _weatherState.value.copy(
                isLoading = true
            )
        }
        viewModelScope.launch {
            weatherRepository.getCurrentWeather(
                CurrentWeatherRequest(
                    latitude = _weatherState.value.location?.latitude ?: 0.0,
                    longitude = _weatherState.value.location?.longitude ?: 0.0
                )
            ).collect { result ->
                when (result) {
                    is ResponseResource.Error -> {
                        _weatherState.update {
                            _weatherState.value.copy(
                                isLoading = false,
                                authException = if (result.error is AuthenticationException) {
                                    AuthenticationException(
                                        result.error.message,
                                        positiveAction = { dismissError() },
                                        negativeAction = { dismissError() }
                                    )
                                } else {
                                    null
                                },
                                error = if (result.error !is AuthenticationException) {
                                    UiError(
                                        errorMessage = result.error.message.orEmpty(),
                                        positiveAction = { dismissError() },
                                        negativeAction = { dismissError() }
                                    )
                                } else {
                                    null
                                }
                            )
                        }
                    }

                    is ResponseResource.Success -> {
                        _weatherState.update {
                            _weatherState.value.copy(
                                isLoading = false,
                                data = result.data.toUiWeatherItem(),
                                authException = null,
                                error = null
                            )
                        }
                    }
                }
            }
        }
    }

    private fun searchForCity(city: String) {
        _weatherState.update {
            _weatherState.value.copy(
                isCitiesLoading = true
            )
        }
        viewModelScope.launch {
            locationRepository.geocodeCity(
                city = city
            ).collect { result ->
                when (result) {
                    is ResponseResource.Error -> {
                        _weatherState.update {
                            _weatherState.value.copy(
                                isCitiesLoading = false,
                                authException = null,
                                error = UiError(
                                    errorMessage = result.error.message.orEmpty(),
                                    positiveAction = { dismissError() },
                                    negativeAction = { dismissError() }
                                )
                            )
                        }
                    }

                    is ResponseResource.Success -> {
                        _weatherState.update {
                            _weatherState.value.copy(
                                isCitiesLoading = false,
                                cities = result.data,
                                showCities = result.data.isEmpty().not()
                            )
                        }
                    }
                }
            }
        }
    }

    private fun getWeatherForCity(city: City) {
        _weatherState.update {
            _weatherState.value.copy(
                city = city.name,
                country = city.country,
                fullAddressText = city.name.plus(", ").plus(city.country),
                location = city.location,
                showCities = false
            )
        }
        getCurrentWeather()
    }

    private fun setLocationServicesTurnedOff() {
        _weatherState.update {
            _weatherState.value.copy(
                locationServicesTurnedOff = true,
                exception = null,
                authException = null,
                error = null
            )
        }
    }

    private fun dismissError() {
        _weatherState.update {
            _weatherState.value.copy(
                authException = null,
                error = null
            )
        }
    }
}
