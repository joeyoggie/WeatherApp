package com.musalasoft.location.data.remote.dto.geocodeCity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias GeocodeCityResponseDto = Array<GeocodeCityItemResponseDto>

@Serializable
data class GeocodeCityItemResponseDto(
    @SerialName("name")
    val name: String?,
    @SerialName("lat")
    val lat: Double?,
    @SerialName("lon")
    val lon: Double?,
    @SerialName("country")
    val country: String?,
    @SerialName("state")
    val state: String?
)
