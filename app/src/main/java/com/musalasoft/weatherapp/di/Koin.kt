package com.musalasoft.weatherapp.di

import com.musalasoft.core.utilities.di.utilitiesModule
import com.musalasoft.location.di.locationModule
import com.musalasoft.weather.di.weatherModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

internal fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            appModule(),
            platformModule(),
            utilitiesModule,
            locationModule,
            weatherModule
        )
    }
