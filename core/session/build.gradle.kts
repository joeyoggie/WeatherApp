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
    namespace = "com.musalasoft.core.session"
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
        implementation(kotlinDateTime)
    }
    implementation(project(":core:localStorage"))
    implementation(Dependencies.Android.viewModelKtx)
}
