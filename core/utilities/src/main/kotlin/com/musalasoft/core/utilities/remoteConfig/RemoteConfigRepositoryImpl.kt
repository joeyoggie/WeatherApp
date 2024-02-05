package com.musalasoft.core.utilities.remoteConfig

class RemoteConfigRepositoryImpl : RemoteConfigRepository {

    suspend fun init() {
    }

    override fun getBoolean(key: String): Boolean {
        return true
    }

    override fun getString(key: String): String {
        return ""
    }

    override fun getInt(key: String): Int {
        return 0
    }

    override fun getBooleanFromJson(key: String, json: String): Boolean {
        return true
    }

    override fun getStringFromJson(key: String, json: String): String {
        return ""
    }

    override fun getIntFromJson(key: String, json: String): Int {
        return 0
    }
}
