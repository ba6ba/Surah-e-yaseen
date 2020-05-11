import dependencies.UIDependencies

plugins {
    id("commons.library-android")
}

dependencies {
	api(UIDependencies.androidAppCompat)
	api(UIDependencies.constraintLayout)
	api(UIDependencies.materialDesignNew)
	api(UIDependencies.swipeRefreshLayout)
	api(UIDependencies.cardView)
	api(UIDependencies.recyclerView)

	api(project(BuildModules.extensions))
	api(project(BuildModules.translations))
}