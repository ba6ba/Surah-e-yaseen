plugins {
    id("commons.library-android")
}

dependencies {
    implementation(project(BuildModules.network))
    implementation(project(BuildModules.storage))
    api(project(BuildModules.daos))
    implementation(project(BuildModules.extensions))
    implementation(project(BuildModules.di))
}