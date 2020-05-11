import dependencies.NavigationDependencies

plugins {
    id("commons.library-android")
}

dependencies {
	api(NavigationDependencies.navigation)
	api(NavigationDependencies.navigationKtx)
	api(NavigationDependencies.navigationDynamicFeature)
}
