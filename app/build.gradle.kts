plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp") version Versions.ksp
    //id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("kotlin-parcelize")
}

android {
    namespace = "com.musalasoft.weatherapp"
    compileSdk = Versions.compileSdk

    defaultConfig {
        applicationId = "com.musalasoft.weatherapp"
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
        versionCode = Versions.versionCode
        versionName = Versions.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            isDebuggable = true
        }
    }
    compileOptions {
        sourceCompatibility = Versions.javaVersion
        targetCompatibility = Versions.javaVersion
    }
    kotlinOptions {
        jvmTarget = Versions.jvmTarget
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "/META-INF/versions/9/previous-compilation-data.bin"
        }
    }
    applicationVariants.all {
        kotlin.sourceSets {
            getByName(name) {
                kotlin.srcDir("build/generated/ksp/$name/kotlin")
            }
        }
    }
}

dependencies {
    implementation(project(":core:session"))
    implementation(project(":core:utilities"))
    implementation(project(":features:location"))
    implementation(project(":features:weather"))
    implementation(Dependencies.Android.libs)
    implementation(Dependencies.Ktor.libs)
    implementation(Dependencies.Koin.android)
    implementation(Dependencies.Koin.compose)

    platformImplementation(Dependencies.Android.firebaseBom)
    ksp(Dependencies.Android.composeDestinationsKsp)

    testImplementation(Dependencies.Android.unitTesting)
    androidTestImplementation(Dependencies.Android.androidTesting)
    debugImplementation(Dependencies.Android.debugImplement)
}
