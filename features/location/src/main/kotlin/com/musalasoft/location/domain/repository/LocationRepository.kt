package com.musalasoft.location.domain.repository

import com.musalasoft.core.utilities.network.ResponseResource
import com.musalasoft.location.domain.model.City
import com.musalasoft.location.domain.model.GeocodedAddress
import com.musalasoft.location.domain.model.Location
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    suspend fun getCurrentLocation(): Flow<ResponseResource<Location>>
    suspend fun geocodeAddress(
        latitude: Double,
        longitude: Double
    ): Flow<ResponseResource<GeocodedAddress>>
    suspend fun geocodeCity(
        city: String
    ): Flow<ResponseResource<List<City>>>
}
