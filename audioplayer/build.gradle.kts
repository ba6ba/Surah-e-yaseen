plugins {
    id("commons.library-android")
}

dependencies {
    implementation(project(BuildModules.ui))
    implementation(project(BuildModules.daos))
}