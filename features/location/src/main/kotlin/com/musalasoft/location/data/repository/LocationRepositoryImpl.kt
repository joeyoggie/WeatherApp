package com.musalasoft.location.data.repository

import com.musalasoft.core.utilities.network.ResponseResource
import com.musalasoft.core.utilities.network.userErrorMessage
import com.musalasoft.location.data.local.LocationSource
import com.musalasoft.location.data.remote.LocationRemote
import com.musalasoft.location.domain.mapper.toCity
import com.musalasoft.location.domain.mapper.toGeocodedAddress
import com.musalasoft.location.domain.model.City
import com.musalasoft.location.domain.model.GeocodedAddress
import com.musalasoft.location.domain.model.Location
import com.musalasoft.location.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class LocationRepositoryImpl (
    private val source: LocationSource,
    private val remote: LocationRemote
) : LocationRepository {

    override suspend fun getCurrentLocation(): Flow<ResponseResource<Location>> = flow<ResponseResource<Location>> {
        source.getCurrentLocation().catch { error ->
            emit(ResponseResource.error(error))
        }.collect { response ->
            emit(response)
        }
    }.catch {
        emit(ResponseResource.error(it))
    }

    override suspend fun geocodeAddress(
        latitude: Double,
        longitude: Double
    ): Flow<ResponseResource<GeocodedAddress>> = flow {
        remote.geocodeAddress(
            latitude = latitude,
            longitude = longitude
        ).catch { error ->
            emit(ResponseResource.error(error.userErrorMessage()))
        }.collect { response ->
            emit(ResponseResource.success(response.toGeocodedAddress()))
        }
    }

    override suspend fun geocodeCity(
        city: String
    ): Flow<ResponseResource<List<City>>> = flow {
        remote.geocodeCity(
            city = city
        ).catch { error ->
            emit(ResponseResource.error(error.userErrorMessage()))
        }.collect { response ->
            emit(ResponseResource.success(response.map { it.toCity() }))
        }
    }
}
