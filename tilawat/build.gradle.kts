plugins {
    id("commons.library-android")
}

dependencies {
	implementation(project(BuildModules.core))
	implementation(project(BuildModules.sidesheet))
	implementation(project(BuildModules.translators))
    implementation(project(BuildModules.data))
}