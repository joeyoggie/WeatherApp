package com.musalasoft.core.utilities.di

import com.musalasoft.core.session.data.local.SessionLocal
import com.musalasoft.core.session.data.local.source.SessionLocalImpl
import org.koin.dsl.module

internal val cacheModule = module {
    single <SessionLocal>  { SessionLocalImpl() }
}
