package com.musalasoft.location.di

import com.musalasoft.location.data.local.LocationSource
import com.musalasoft.location.data.local.source.LocationSourceImpl
import org.koin.dsl.module

internal val localModule = module {
    single<LocationSource> { LocationSourceImpl(get()) }
}
