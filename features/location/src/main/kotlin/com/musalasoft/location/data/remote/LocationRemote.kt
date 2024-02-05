package com.musalasoft.location.data.remote

import com.musalasoft.location.data.remote.dto.geocodeAddress.GeocodeAddressResponseDto
import com.musalasoft.location.data.remote.dto.geocodeCity.GeocodeCityResponseDto
import kotlinx.coroutines.flow.Flow

interface LocationRemote {
    suspend fun geocodeAddress(
        latitude: Double,
        longitude: Double
    ): Flow<GeocodeAddressResponseDto>
    suspend fun geocodeCity(
        city: String
    ): Flow<GeocodeCityResponseDto>
}
