package com.musalasoft.location.data.local

import com.musalasoft.core.utilities.network.ResponseResource
import com.musalasoft.location.domain.model.Location
import kotlinx.coroutines.flow.Flow

interface LocationSource {
    suspend fun getCurrentLocation(): Flow<ResponseResource<Location>>
}
