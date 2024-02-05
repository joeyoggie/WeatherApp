package com.musalasoft.core.session.data.local.source

import com.musalasoft.core.session.Session
import com.musalasoft.core.session.data.local.SessionLocal
import com.musalasoft.core.localstorage.DatastoreStorageImpl

class SessionLocalImpl : SessionLocal {

    override var appLanguage: String
        get() = DatastoreStorageImpl().getString("app_language") ?: Session.LANGUAGE_EN
        set(value) = DatastoreStorageImpl().putString("app_language", value)
}
