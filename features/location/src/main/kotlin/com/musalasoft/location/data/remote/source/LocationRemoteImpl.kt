package com.musalasoft.location.data.remote.source

import com.musalasoft.core.session.data.local.SessionLocal
import com.musalasoft.core.utilities.network.performCall
import com.musalasoft.location.common.LocationConstants
import com.musalasoft.location.data.remote.LocationRemote
import com.musalasoft.location.data.remote.dto.geocodeAddress.GeocodeAddressResponseDto
import com.musalasoft.location.data.remote.dto.geocodeCity.GeocodeCityResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LocationRemoteImpl (
    private val client: HttpClient,
    private val session: SessionLocal
) : LocationRemote {

    override suspend fun geocodeAddress(
        latitude: Double,
        longitude: Double
    ): Flow<GeocodeAddressResponseDto> = flow {
        emit(
            runCatching {
                val response =
                    client.get {
                        setAttributes {
                            parameter("latlng", "$latitude,$longitude")
                            parameter("key", LocationConstants.GOOGLE_GEOCODING_API_KEY)
                            parameter("language", session.appLanguage)
                        }
                        performCall(
                            baseUrl = LocationConstants.GOOGLE_GEOCODING_BASE_URL,
                            path = LocationConstants.GEOCODE_ADDRESS,
                            acceptLanguage = session.appLanguage
                        )
                    }.body<GeocodeAddressResponseDto>()
                response
            }.getOrElse { error ->
                throw error
            }
        )
    }

    override suspend fun geocodeCity(
        city: String
    ): Flow<GeocodeCityResponseDto> = flow {
        emit(
            runCatching {
                val response =
                    client.get {
                        setAttributes {
                            parameter("q", city)
                            parameter("limit", "10")
                            parameter("appid", LocationConstants.API_TOKEN)
                            parameter("lang", session.appLanguage)
                        }
                        performCall(
                            baseUrl = LocationConstants.OPEN_WEATHER_BASE_URL,
                            path = LocationConstants.GEOCODE_CITY,
                            acceptLanguage = session.appLanguage
                        )
                    }.body<GeocodeCityResponseDto>()
                response
            }.getOrElse { error ->
                throw error
            }
        )
    }
}
