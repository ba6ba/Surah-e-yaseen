plugins {
    id("commons.library-android")
}

dependencies {
	implementation(project(BuildModules.core))
	implementation(project(BuildModules.listpager))
    implementation(project(BuildModules.data))
}