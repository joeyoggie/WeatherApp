package com.musalasoft.location.di

import org.koin.dsl.module

val locationModule = module {
    includes(
        localModule,
        remoteModule,
        repositoryModule
    )
}
