package com.musalasoft.weather.di

import org.koin.dsl.module

val weatherModule = module {
    includes(
        remoteModule,
        repositoryModule,
        viewModelModule
    )
}
