package com.musalasoft.weather.di

import com.musalasoft.weather.presentation.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

internal val viewModelModule = module {
    viewModelOf(::WeatherViewModel)
}
