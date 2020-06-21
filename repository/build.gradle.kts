plugins {
    id("commons.library-android")
}

dependencies {
    implementation(project(BuildModules.network))
    implementation(project(BuildModules.storage))
    implementation(project(BuildModules.repositoryDaos))
    implementation(project(BuildModules.extensions))
    implementation(project(BuildModules.di))
}