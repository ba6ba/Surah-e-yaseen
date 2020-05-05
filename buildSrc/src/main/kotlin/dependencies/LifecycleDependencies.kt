package dependencies

import BuildDependenciesVersions

object LifecycleDependencies {
    const val lifeCycleExtensions = "androidx.lifecycle:lifecycle-runtime-ktx:${BuildDependenciesVersions.lifeCycle}"
    const val lifeCycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${BuildDependenciesVersions.lifeCycle}"
    const val lifeCycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${BuildDependenciesVersions.lifeCycle}"
    const val lifeCycleSavedStateViewModel = "androidx.lifecycle:lifecycle-viewmodel-savedstate:${BuildDependenciesVersions.lifeCycle}"

    // kapt lifecycle compiler
    const val lifeCycleCompiler = "androidx.lifecycle:lifecycle-compiler:${BuildDependenciesVersions.lifeCycle}"
}