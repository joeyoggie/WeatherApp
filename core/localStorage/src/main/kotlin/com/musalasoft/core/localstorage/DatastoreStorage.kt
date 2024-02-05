package com.musalasoft.core.localstorage

import android.content.Context
import kotlinx.coroutines.flow.Flow

interface DatastoreStorage {

    fun initDatastore(context: Context)

    fun getInt(key: String): Int?
    fun putInt(key: String, value: Int?)

    fun getString(key: String): String?
    fun putString(key: String, value: String?)

    fun getBoolean(key: String): Boolean?
    fun putBoolean(key: String, value: Boolean?)

    fun getIntFlow(key: String): Flow<Int?>

    fun clearAll()
}
