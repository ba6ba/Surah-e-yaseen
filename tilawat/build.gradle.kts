plugins {
    id("commons.library-android")
}

dependencies {
	implementation(project(BuildModules.core))
	implementation(project(BuildModules.sidesheet))
	implementation(project(BuildModules.reciters))
    implementation(project(BuildModules.data))
    implementation(project(BuildModules.media))
    implementation(project(BuildModules.audioplayer))
    implementation(project(BuildModules.shared))
}