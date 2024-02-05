plugins {
    id("com.android.application") version Versions.gradlePluginVersion apply false
    id("com.android.library") version Versions.gradlePluginVersion apply false
    id("org.jetbrains.kotlin.android") version Versions.kotlin apply false
    id("com.google.gms.google-services") version Versions.gmsGoogleServices apply false
    id("com.google.firebase.crashlytics") version Versions.firebaseCrashlyticsGradle apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    dependencies {
        classpath(Dependencies.Android.gmsGoogleServices)
        classpath(Dependencies.Android.firebaseCrashlyticsGradle)
        //classpath(Dependencies.Kotlinx.kotlinSerializationGradle)
    }
}