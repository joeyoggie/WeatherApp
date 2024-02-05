package com.musalasoft.core.utilities.di

import org.koin.dsl.module

val utilitiesModule = module {
        includes(
                cacheModule,
                remoteModule
        )
}
