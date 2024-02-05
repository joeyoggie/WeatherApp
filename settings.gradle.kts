pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_PROJECT)
    repositories {
        google()
        mavenCentral()
        maven( url = uri("https://jitpack.io"))
    }
}

rootProject.name = "WeatherApp"
include(":app")
include(":core:localStorage")
include(":core:session")
include(":core:utilities")

