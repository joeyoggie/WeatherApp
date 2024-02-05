package com.musalasoft.core.utilities.di

import com.musalasoft.core.utilities.network.ktorHttpClient
import io.ktor.client.HttpClient
import org.koin.dsl.module

internal val remoteModule = module {
    single <HttpClient>  { ktorHttpClient(get()) }
}
