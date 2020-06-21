import dependencies.ThirdPartyDependencies
import dependencies.UIDependencies

plugins {
    id("commons.library-android")
}

dependencies {
    implementation(project(BuildModules.extensions))
    implementation(project(BuildModules.network))
    implementation(project(BuildModules.shared))
    implementation(project(BuildModules.daos))
    implementation(project(BuildModules.di))
    implementation(UIDependencies.recyclerView)
    implementation(ThirdPartyDependencies.exoPlayerCore)
    implementation(ThirdPartyDependencies.exoPlayerUI)
    implementation(ThirdPartyDependencies.exoPlayerMediaSession)
}