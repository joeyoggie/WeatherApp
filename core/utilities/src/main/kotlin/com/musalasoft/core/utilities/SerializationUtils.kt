package com.musalasoft.core.utilities

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@OptIn(ExperimentalSerializationApi::class)
val json = Json {
    isLenient = true
    prettyPrint = true
    ignoreUnknownKeys = true
    explicitNulls = true
}

inline fun <reified T> T.toJson(): String = json.encodeToString(this)

inline fun <reified T> String.toModel(): T? = try {
    json.decodeFromString<T>(this)
} catch (e: Exception) {
    null
}