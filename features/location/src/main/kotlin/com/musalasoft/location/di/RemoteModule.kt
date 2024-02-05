package com.musalasoft.location.di

import com.musalasoft.location.data.remote.LocationRemote
import com.musalasoft.location.data.remote.source.LocationRemoteImpl
import org.koin.dsl.module

internal val remoteModule = module {
    single<LocationRemote> { LocationRemoteImpl(get(), get()) }
}
