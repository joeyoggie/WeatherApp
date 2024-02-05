plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = Versions.compileSdk
    defaultConfig {
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
    }
    namespace = "com.musalasoft.core.utilities"
    compileOptions {
        sourceCompatibility = Versions.javaVersion
        targetCompatibility = Versions.javaVersion
    }
    kotlin {
        jvmToolchain(Versions.jvmToolchain)
    }
}

dependencies {
    with(Dependencies.Ktor) {
        //implementation(libs)
        api(ktorCore)
        api(contentNegotiation)
        api(ktorLogging)
        api(ktorSerializationJson)
        api(clientAndroid)
    }
    with(Dependencies.Kotlinx) {
        implementation(coroutinesCore)
        implementation(kotlinxSerialization)
        implementation(kotlinxSerializationJson)
        implementation(kotlinDateTime)
    }
    implementation(project(":core:session"))
    implementation(Dependencies.Android.viewModelKtx)
    implementation(Dependencies.Android.gson)
    with(Dependencies.Koin) {
        implementation(core)
        implementation(android)
    }
}
