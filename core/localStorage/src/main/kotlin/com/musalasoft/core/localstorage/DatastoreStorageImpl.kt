package com.musalasoft.core.localstorage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.atomicfu.locks.SynchronizedObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import okio.Path.Companion.toPath

class DatastoreStorageImpl: DatastoreStorage {
    private var datastore: DataStore<Preferences>? = null

    override fun initDatastore(context: Context) {
        datastore = getDataStore(context)
    }

    override fun getInt(key: String): Int? =
        runBlocking { datastore?.data?.first()?.get(intPreferencesKey(key)) }

    override fun putInt(key: String, value: Int?) {
        runBlocking {
            datastore?.edit {
                it[intPreferencesKey(key)] = value ?: 0
            }
        }
    }

    override fun getString(key: String): String? =
        runBlocking { datastore?.data?.first()?.get(stringPreferencesKey(key)) }

    override fun putString(key: String, value: String?) {
        runBlocking {
            datastore?.edit {
                it[stringPreferencesKey(key)] = value ?: ""
            }
        }
    }

    override fun getBoolean(key: String): Boolean? =
        runBlocking { datastore?.data?.first()?.get(booleanPreferencesKey(key)) }

    override fun putBoolean(key: String, value: Boolean?) {
        runBlocking {
            datastore?.edit {
                it[booleanPreferencesKey(key)] = value ?: false
            }
        }
    }

    override fun getIntFlow(key: String): Flow<Int?> =
        datastore?.data?.map { it[intPreferencesKey(key)] } ?: flow {  }

    override fun clearAll() {
        runBlocking {
            datastore?.edit { it.clear() }
        }
    }
}

fun getDataStore(context: Context): DataStore<Preferences> = createDataStore(
    producePath = { context.filesDir.resolve(dataStoreFileName).absolutePath }
)

const val dataStoreFileName = "musalasoft.preferences_pb"
private lateinit var dataStore: DataStore<Preferences>
private val lock = SynchronizedObject()
fun createDataStore(producePath: () -> String): DataStore<Preferences> =
    synchronized(lock) {
        if (::dataStore.isInitialized) {
            dataStore
        } else {
            PreferenceDataStoreFactory.createWithPath(produceFile = { producePath().toPath() })
                .also { dataStore = it }
        }
    }
