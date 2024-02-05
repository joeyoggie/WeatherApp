import org.gradle.api.artifacts.dsl.DependencyHandler

/**
 * Created by Ahmed Ibrahim on 16/07/2022
 */

object Dependencies {
    object Android {
        // Libs
        const val coreKTX = "androidx.core:core-ktx:${Versions.coreKTX}"

        const val composeUI = "androidx.compose.ui:ui:${Versions.composeUI}"
        const val composeUtil = "androidx.compose.ui:ui-util:${Versions.composeUI}"
        const val composeTooling = "androidx.compose.ui:ui-tooling-preview:${Versions.composeUI}"
        const val composeMaterial = "androidx.compose.material:material:${Versions.composeMaterial}"
        const val composeMaterial3 = "androidx.compose.material3:material3:${Versions.composeMaterial3}"
        const val composeFoundation = "androidx.compose.foundation:foundation:${Versions.composeFoundation}"

        const val composeDestinations = "io.github.raamcosta.compose-destinations:animations-core:${Versions.composeDestinations}"
        const val composeDestinationsKsp = "io.github.raamcosta.compose-destinations:ksp:${Versions.composeDestinations}"

        const val composeActivity = "androidx.activity:activity-compose:${Versions.activityCompose}"
        const val composeNavigation = "androidx.navigation:navigation-compose:${Versions.composeNavigation}"
        const val composeViewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.composeViewModel}"

        const val lifecycleRuntimeKTX =
            "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntimeKTX}"
        const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleVersion}"
        const val accompanistPermissions = "com.google.accompanist:accompanist-permissions:${Versions.accompanist}"
        const val accompanistSystemUiController = "com.google.accompanist:accompanist-systemuicontroller:${Versions.accompanist}"

        const val gmsGoogleServices = "com.google.gms:google-services:${Versions.gmsGoogleServices}"
        const val firebaseCrashlyticsGradle = "com.google.firebase:firebase-crashlytics-gradle:${Versions.firebaseCrashlyticsGradle}"
        const val firebaseBom = "com.google.firebase:firebase-bom:${Versions.firebaseBom}"

        const val firebaseCrashlytics = "com.google.firebase:firebase-crashlytics-ktx"
        const val firebaseAnalytics = "com.google.firebase:firebase-analytics-ktx"
        const val firebaseMessaging = "com.google.firebase:firebase-messaging-ktx"
        const val firebaseInstallations = "com.google.firebase:firebase-installations-ktx"
        const val coil =  "io.coil-kt:coil-compose:${Versions.coil}"
        const val coilGif = "io.coil-kt:coil-gif:${Versions.coil}"
        const val accompanistPlaceHolder = "com.google.accompanist:accompanist-placeholder-material:${Versions.accompanist}"
        const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
        const val pager = "com.google.accompanist:accompanist-pager:${Versions.pagerVersion}"
        const val lottieCompose = "com.airbnb.android:lottie-compose:${Versions.lottieCompose}"
        const val desugar = "com.android.tools:desugar_jdk_libs:${Versions.desugar}"
        const val startupRuntime = "androidx.startup:startup-runtime:${Versions.startupRunTime}"
        const val gson = "com.google.code.gson:gson:${Versions.gson}"
        const val androidXBrowser = "androidx.browser:browser:${Versions.androidXBrowser}"
        const val googleLocation = "com.google.android.gms:play-services-location:${Versions.googleLocation}"
        private const val googleMaps = "com.google.android.gms:play-services-maps:${Versions.googleMaps}"
        private const val googleMapsCompose = "com.google.maps.android:maps-compose:${Versions.googleMapsCompose}"

        val libs = arrayListOf<String>().apply {
            add(coreKTX)
            add(composeUI)
            add(composeUtil)
            add(composeTooling)
            add(composeMaterial)
            add(composeMaterial3)
            add(composeFoundation)

            add(composeDestinations)

            add(composeActivity)
            add(composeNavigation)
            add(composeViewModel)

            add(lifecycleRuntimeKTX)
            add(viewModelKtx)
            add(accompanistPermissions)
            add(accompanistSystemUiController)
            add(firebaseCrashlytics)
            add(firebaseAnalytics)
            add(firebaseMessaging)
            add(firebaseInstallations)
            add(coil)
            add(coilGif)
            add(accompanistPlaceHolder)
            add(appCompat)
            add(pager)
            add(lottieCompose)
            add(desugar)
            add(startupRuntime)
            add(gson)
            add(androidXBrowser)
            add(googleLocation)
            add(googleMaps)
            add(googleMapsCompose)
        }

        // Testing
        private const val jUnit = "junit:junit:${Versions.jUnit}"
        val unitTesting = arrayListOf<String>().apply {
            add(jUnit)
        }

        // Android Testing
        private const val extJUnit = "androidx.test.ext:junit:${Versions.extJUnit}"
        private const val espressoCore =
            "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
        private const val composeUITest = "androidx.compose.ui:ui-test-junit4:${Versions.composeUI}"
        val androidTesting = arrayListOf<String>().apply {
            extJUnit
            espressoCore
            composeUITest
        }

        // Debug Impl
        private const val composeToolingTest = "androidx.compose.ui:ui-tooling:${Versions.composeUI}"
        private const val composeManifest =
            "androidx.compose.ui:ui-test-manifest:${Versions.composeUI}"
        val debugImplement = arrayListOf<String>().apply {
            add(composeToolingTest)
            add(composeManifest)
        }
    }

    object Kotlinx {
        const val kotlinxSerialization =
            "org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.kotlinxSerialization}"
        const val kotlinxSerializationJson =
            "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinxSerialization}"
        const val coroutinesCore =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinxCoroutines}"
        const val kotlinDateTime =
            "org.jetbrains.kotlinx:kotlinx-datetime:${Versions.kotlinxDateTime}"
        const val kotlinSerializationGradle = "org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin}"
    }

    object Koin {
        const val core = "io.insert-koin:koin-core:${Versions.koinVersion}"
        const val test = "io.insert-koin:koin-test:${Versions.koinVersion}"
        const val testJUnit4 = "io.insert-koin:koin-test-junit4:${Versions.koinVersion}"
        const val android = "io.insert-koin:koin-android:${Versions.koinAndroid}"
        const val compose = "io.insert-koin:koin-androidx-compose:${Versions.koinAndroid}"
    }

    object Ktor {
        const val ktorCore = "io.ktor:ktor-client-core:${Versions.ktor}"
        const val contentNegotiation =
            "io.ktor:ktor-client-content-negotiation:${Versions.ktor}"
        const val ktorLogging = "io.ktor:ktor-client-logging:${Versions.ktor}"
        const val ktorSerializationJson =
            "io.ktor:ktor-serialization-kotlinx-json:${Versions.ktor}"
        const val clientAndroid = "io.ktor:ktor-client-android:${Versions.ktor}"

        val libs = arrayListOf<String>().apply {
            add(ktorCore)
            add(contentNegotiation)
            add(ktorLogging)
            add(ktorSerializationJson)
            add(clientAndroid)
        }
    }

    object Multiplatform {
        const val datastore = "androidx.datastore:datastore-preferences:${Versions.datastore}"
    }
}


//util functions for adding the different type dependencies from build.gradle file
fun DependencyHandler.kapt(list: List<String>) {
    list.forEach { dependency ->
        add("kapt", dependency)
    }
}

fun DependencyHandler.ksp(list: List<String>) {
    list.forEach { dependency ->
        add("ksp", dependency)
    }
}

fun DependencyHandler.implementation(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

fun DependencyHandler.platformImplementation(dependency: String) {
    add("implementation", platform(dependency))
}

fun DependencyHandler.platformImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", platform(dependency))
    }
}

fun DependencyHandler.androidTestImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("androidTestImplementation", dependency)
    }
}

fun DependencyHandler.testImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("testImplementation", dependency)
    }
}

fun DependencyHandler.debugImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("debugImplementation", dependency)
    }
}