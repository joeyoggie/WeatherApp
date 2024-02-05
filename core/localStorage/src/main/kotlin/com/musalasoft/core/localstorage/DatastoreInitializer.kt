package com.musalasoft.core.localstorage

import android.content.Context
import androidx.startup.Initializer

class DatastoreInitializer : Initializer<DatastoreStorage> {
    override fun create(context: Context): DatastoreStorage {
        DatastoreStorageImpl().initDatastore(context)
        return DatastoreStorageImpl()
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        // No dependencies on other libraries.
        return emptyList()
    }
}
