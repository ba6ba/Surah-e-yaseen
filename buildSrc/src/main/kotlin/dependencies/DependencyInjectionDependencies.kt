package dependencies

import BuildDependenciesVersions

object DependencyInjectionDependencies {
    val koinAndroid = "org.koin:koin-android:${BuildDependenciesVersions.koin}"
    val koinAndroidScope = "org.koin:koin-androidx-scope:${BuildDependenciesVersions.koin}"
    val koinViewModel = "org.koin:koin-androidx-viewmodel:${BuildDependenciesVersions.koin}"
    val koinFragment = "org.koin:koin-androidx-fragment:${BuildDependenciesVersions.koin}"
}