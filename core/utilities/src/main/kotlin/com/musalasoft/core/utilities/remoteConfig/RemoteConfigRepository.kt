package com.musalasoft.core.utilities.remoteConfig

interface RemoteConfigRepository {
    fun getBoolean(key: String): Boolean
    fun getString(key: String): String
    fun getInt(key: String): Int

    fun getBooleanFromJson(key: String, json: String): Boolean
    fun getStringFromJson(key: String, json: String): String
    fun getIntFromJson(key: String, json: String): Int
}
