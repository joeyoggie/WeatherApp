package com.musalasoft.location.di

import com.musalasoft.location.data.repository.LocationRepositoryImpl
import com.musalasoft.location.domain.repository.LocationRepository
import org.koin.dsl.module

internal val repositoryModule = module {
    single<LocationRepository> { LocationRepositoryImpl(get(), get()) }
}
