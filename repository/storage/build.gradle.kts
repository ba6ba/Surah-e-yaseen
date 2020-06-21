import dependencies.RoomDependencies

plugins {
    id("commons.library-android")
}

dependencies {
    implementation(RoomDependencies.roomCompiler)
    implementation(RoomDependencies.roomKtx)
    implementation(RoomDependencies.roomRuntime)
    implementation(project(BuildModules.extensions))
    implementation(project(BuildModules.shared))
    implementation(project(BuildModules.di))
    implementation(project(BuildModules.repositoryDaos))
}