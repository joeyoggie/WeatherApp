package com.musalasoft.location.domain.mapper

import com.musalasoft.location.data.remote.dto.geocodeAddress.GeocodeAddressResponseDto
import com.musalasoft.location.domain.model.GeocodedAddress

/**
    refer to official Google Geocoding API documentation for the below mapping https://developers.google.com/maps/documentation/geocoding/requests-reverse-geocoding\
 **/
fun GeocodeAddressResponseDto.toGeocodedAddress() = GeocodedAddress(
    fullAddressText = this.results?.firstOrNull()?.formattedAddress ?: "",
    building = this.results?.firstOrNull()?.addressComponents?.firstOrNull {
        it.types?.contains("street_number") == true
    }?.longName,
    street = this.results?.firstOrNull()?.addressComponents?.firstOrNull {
        it.types?.contains("route") == true
    }?.longName,
    area = this.results?.firstOrNull()?.addressComponents?.firstOrNull {
        it.types?.contains("sublocality") == true
    }?.longName,
    city = this.results?.firstOrNull()?.addressComponents?.firstOrNull {
        it.types?.contains("locality") == true || it.types?.contains("administrative_area_level_2") == true
    }?.longName,
    province = this.results?.firstOrNull()?.addressComponents?.firstOrNull {
        it.types?.contains("administrative_area_level_1") == true
    }?.longName,
    country = this.results?.firstOrNull()?.addressComponents?.firstOrNull {
        it.types?.contains("country") == true
    }?.longName,
    zipCode = this.results?.firstOrNull()?.addressComponents?.firstOrNull {
        it.types?.contains("postal_code") == true
    }?.longName,
    latitude = this.results?.firstOrNull()?.geometry?.location?.latitude ?: 0.0,
    longitude = this.results?.firstOrNull()?.geometry?.location?.longitude ?: 0.0
)