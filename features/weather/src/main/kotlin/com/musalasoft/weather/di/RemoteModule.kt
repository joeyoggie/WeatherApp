package com.musalasoft.weather.di

import com.musalasoft.weather.data.remote.CurrentWeatherRemote
import com.musalasoft.weather.data.remote.source.CurrentWeatherRemoteImpl
import org.koin.dsl.module

internal val remoteModule = module {
    single<CurrentWeatherRemote> { CurrentWeatherRemoteImpl(get(), get()) }
}
