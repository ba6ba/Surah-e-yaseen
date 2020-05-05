plugins {
    id("commons.library-android")
}

dependencies {
	implementation(project(BuildModules.core))
	implementation(project(BuildModules.tilawat))
	implementation(project(BuildModules.recitation))
}