plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("plugin.serialization") version Versions.kotlin
}

android {
    compileSdk = Versions.compileSdk
    defaultConfig {
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
    }
    namespace = "com.musalasoft.features.location"
    compileOptions {
        sourceCompatibility = Versions.javaVersion
        targetCompatibility = Versions.javaVersion
    }
    kotlin {
        jvmToolchain(Versions.jvmToolchain)
    }
}

dependencies {
    with(Dependencies.Kotlinx) {
        implementation(coroutinesCore)
        implementation(kotlinxSerialization)
        implementation(kotlinxSerializationJson)
        implementation(kotlinDateTime)
    }
    implementation(project(":core:session"))
    implementation(project(":core:utilities"))
    implementation(Dependencies.Android.viewModelKtx)
    implementation(Dependencies.Android.gson)
    implementation(Dependencies.Android.googleLocation)
    implementation(Dependencies.Android.startupRuntime)
    with(Dependencies.Koin) {
        implementation(core)
        implementation(android)
    }
}
