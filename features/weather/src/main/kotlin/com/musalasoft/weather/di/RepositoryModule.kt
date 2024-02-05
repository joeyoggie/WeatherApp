package com.musalasoft.weather.di

import com.musalasoft.weather.data.repository.WeatherRepositoryImpl
import com.musalasoft.weather.domain.repository.WeatherRepository
import org.koin.dsl.module

internal val repositoryModule = module {
    single<WeatherRepository> { WeatherRepositoryImpl(get(), get()) }
}
