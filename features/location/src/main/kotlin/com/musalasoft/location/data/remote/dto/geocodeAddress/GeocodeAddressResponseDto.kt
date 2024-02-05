package com.musalasoft.location.data.remote.dto.geocodeAddress

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GeocodeAddressResponseDto(
    @SerialName("status")
    val status: String? = null,
    @SerialName("results")
    val results: List<GeocodeAddressResultDto>? = null
) {
    @Serializable
    data class GeocodeAddressResultDto(
        @SerialName("address_components")
        val addressComponents: List<AddressComponentDto>? = null,
        @SerialName("formatted_address")
        val formattedAddress: String? = null,
        @SerialName("geometry")
        val geometry: AddressGeometryDto? = null
    ) {
        @Serializable
        data class AddressComponentDto(
            @SerialName("long_name")
            val longName: String? = null,
            @SerialName("short_name")
            val shortName: String? = null,
            @SerialName("types")
            val types: List<String>? = null
        )

        @Serializable
        data class AddressGeometryDto(
            @SerialName("location")
            val location: AddressLocationDto? = null
        ) {
            @Serializable
            data class AddressLocationDto(
                @SerialName("lat")
                val latitude: Double? = null,
                @SerialName("lng")
                val longitude: Double? = null
            )
        }
    }
}
