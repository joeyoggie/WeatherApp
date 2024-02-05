package com.musalasoft.location.data.local.source

import com.musalasoft.core.utilities.network.ResponseResource
import com.musalasoft.location.domain.model.Location
import kotlinx.coroutines.flow.*
import android.annotation.SuppressLint
import android.content.Context
import android.os.Looper
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.location.*
import com.musalasoft.location.data.local.LocationSource
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

@SuppressLint("StaticFieldLeak")
class LocationSourceImpl(
    private val mContext: Context
): LocationSource {

    @SuppressLint("MissingPermission")
    override suspend fun getCurrentLocation(): Flow<ResponseResource<Location>> = callbackFlow {
        var fusedLocationClient: FusedLocationProviderClient? = null

        val locationCallback: LocationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                if (locationResult.lastLocation != null) {
                    trySend(
                        ResponseResource.success(
                            Location(
                                latitude = locationResult.lastLocation!!.latitude,
                                longitude = locationResult.lastLocation!!.longitude
                            )
                        )
                    )
                    fusedLocationClient?.removeLocationUpdates(this)
                } else {
                    trySend(
                        ResponseResource.error(
                            Exception("Unable to get location")
                        )
                    )
                }
            }
        }

        val locationRequest = createLocationRequest()

        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)

        val result = LocationServices
            .getSettingsClient(mContext!!.applicationContext)
            .checkLocationSettings(builder.build())

        result.addOnSuccessListener {
            // All location settings are satisfied. The client can initialize location
            // requests here.
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(mContext!!.applicationContext)
            fusedLocationClient?.lastLocation
                ?.addOnSuccessListener { location: android.location.Location? ->
                    // Got last known location. In some rare situations this can be null.
                    if (location != null) {
                        trySend(
                            ResponseResource.success(
                                Location(
                                    latitude = location.latitude,
                                    longitude = location.longitude
                                )
                            )
                        )
                        fusedLocationClient?.removeLocationUpdates(locationCallback)
                    } else {
                        fusedLocationClient?.requestLocationUpdates(
                            locationRequest,
                            locationCallback,
                            Looper.getMainLooper()
                        )
                    }
                }
        }

        result.addOnFailureListener {
            if (it is ApiException) {
                when (it.statusCode) {
                    LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> {
                        // Location settings are not satisfied. But could be fixed by showing the
                        // user a dialog.
                        // "Please turn on location services"
                        trySend(
                            ResponseResource.error(
                                it
                            )
                        )
                    }
                    LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {
                        // Location settings are not satisfied. However, we have no way to fix the
                        // settings so we won't show the dialog.
                        // "Location services not available"
                        trySend(
                            ResponseResource.error(
                                it
                            )
                        )
                    }
                    else -> {
                        // Location settings are not satisfied. However, we have no way to fix the
                        // settings so we won't show the dialog.
                        // "Location services not available"
                        trySend(
                            ResponseResource.error(
                                it
                            )
                        )
                    }
                }
            } else {
                // "Please turn on location services"
                trySend(
                    ResponseResource.error(
                        it
                    )
                )
            }
        }

        awaitClose {
            fusedLocationClient?.removeLocationUpdates(locationCallback)
        }
    }

    private fun createLocationRequest(): LocationRequest {
        return LocationRequest.create().apply {
            interval = 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
    }
}
