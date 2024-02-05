package com.musalasoft.location.domain.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class Location(
    @SerialName("latitude")
    val latitude: Double,
    @SerialName("longitude")
    val longitude: Double
)
