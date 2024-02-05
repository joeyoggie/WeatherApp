package com.musalasoft.location.data.local.source

import android.content.Context
import androidx.startup.Initializer

class LocationInitializer : Initializer<LocationSourceImpl> {
    override fun create(context: Context): LocationSourceImpl {
        return LocationSourceImpl(context)
    }
    override fun dependencies(): List<Class<out Initializer<*>>> {
        // No dependencies on other libraries.
        return emptyList()
    }
}
