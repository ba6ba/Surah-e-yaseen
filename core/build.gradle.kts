plugins {
    id("commons.library-android")
}

dependencies {
	api(project(BuildModules.ui))
	api(project(BuildModules.navigation))
	api(project(BuildModules.di))
	api(project(BuildModules.network))
    implementation(project(BuildModules.data))
}