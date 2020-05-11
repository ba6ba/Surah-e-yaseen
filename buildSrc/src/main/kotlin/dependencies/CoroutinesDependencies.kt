package dependencies

import BuildDependenciesVersions

object CoroutinesDependencies {
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${BuildDependenciesVersions.coroutines}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${BuildDependenciesVersions.coroutines}"
}