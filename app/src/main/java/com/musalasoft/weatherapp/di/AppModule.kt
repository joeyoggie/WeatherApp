package com.musalasoft.weatherapp.di

import com.musalasoft.core.utilities.network.ktorHttpClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.dsl.module

fun appModule() = module {
    single { ktorHttpClient(get()) }
    single { CoroutineScope(Dispatchers.Default + SupervisorJob()) }
}
