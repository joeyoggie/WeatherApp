package com.musalasoft.weatherapp.di

import io.ktor.client.engine.android.Android
import org.koin.dsl.module

fun platformModule() = module {
    single { Android.create() }
}
