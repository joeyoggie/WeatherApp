package com.musalasoft.location.domain.mapper

import com.musalasoft.location.data.remote.dto.geocodeCity.GeocodeCityItemResponseDto
import com.musalasoft.location.domain.model.City
import com.musalasoft.location.domain.model.Location

fun GeocodeCityItemResponseDto.toCity() = City(
    name = this.name.orEmpty(),
    country = this.country.orEmpty(),
    location = Location(
        latitude = this.lat ?: 0.0,
        longitude = this.lon ?: 0.0
    )
)
