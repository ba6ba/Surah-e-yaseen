import dependencies.DependencyInjectionDependencies

plugins {
    id("commons.library-android")
}

dependencies {
	api(DependencyInjectionDependencies.koinViewModel)
	api(DependencyInjectionDependencies.koinAndroid)
	api(DependencyInjectionDependencies.koinAndroidScope)
	api(DependencyInjectionDependencies.koinFragment)
}
