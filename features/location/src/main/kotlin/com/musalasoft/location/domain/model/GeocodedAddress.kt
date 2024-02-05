package com.musalasoft.location.domain.model

data class GeocodedAddress(
    val fullAddressText: String?,
    val building: String?,
    val street: String?,
    val area: String?,
    val city: String?,
    val province: String?,
    val country: String?,
    val zipCode: String?,
    val latitude: Double?,
    val longitude: Double?
)
