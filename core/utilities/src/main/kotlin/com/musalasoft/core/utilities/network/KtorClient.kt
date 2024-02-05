package com.musalasoft.core.utilities.network

import com.musalasoft.core.utilities.constants.Constants
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.encodedPath
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

@OptIn(ExperimentalSerializationApi::class)
fun ktorHttpClient(
    httpClientEngine: HttpClientEngine
) = HttpClient(httpClientEngine) {
    expectSuccess = true
    val enableNetworkLogs = true // Make it true in case you want to check network logs, PS:- this should only be true in debug

    if (enableNetworkLogs) {
        install(Logging) {
            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) {
                    println("Network Logger-> $message")
                }
            }
        }
    }

    install(ContentNegotiation) {
        json(Json {
            isLenient = true
            prettyPrint = true
            ignoreUnknownKeys = true
            explicitNulls = false
        })
    }
    install(ResponseObserver) {
        onResponse { response ->
            if (enableNetworkLogs)
                println("Http status: ${response.status.value}")
        }
    }

    install(DefaultRequest) {
        header(HttpHeaders.ContentType, ContentType.Application.Json)
    }

    install(HttpRequestRetry) {
        maxRetries = 2
        exponentialDelay()
        retryOnException(maxRetries = 2, retryOnTimeout = true)
        retryOnServerErrors(maxRetries = 2)
    }

    install(HttpTimeout) {
        connectTimeoutMillis = Constants.NETWORK_TIMEOUT
        requestTimeoutMillis = Constants.NETWORK_TIMEOUT
        socketTimeoutMillis = Constants.NETWORK_TIMEOUT
    }
}

fun HttpRequestBuilder.performCall(
    baseUrl: String,
    path: String,
    acceptLanguage: String
) {
    url {
        takeFrom(baseUrl)
        encodedPath = path
        headers {
            append(
                HttpHeaders.AcceptLanguage, acceptLanguage
            )
        }
    }
}
